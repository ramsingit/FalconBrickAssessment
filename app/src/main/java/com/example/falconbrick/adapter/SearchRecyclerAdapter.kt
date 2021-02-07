package com.example.falconbrick.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.falconbrick.databinding.ItemUnitBinding
import com.example.falconbrick.helpers.StringHelper
import com.example.falconbrick.model.ActivityUnit
import java.util.*
import kotlin.collections.ArrayList

class SearchRecyclerAdapter : RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>(),
    Filterable {

    private lateinit var activities: List<ActivityUnit>
    private var filteredList: List<ActivityUnit> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val bindingItem =
            ItemUnitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        if(viewType == 1) {
            bindingItem.listItem.visibility = View.VISIBLE
            bindingItem.emptyText.visibility = View.GONE
        }
        else{
            bindingItem.emptyText.visibility = View.VISIBLE
            bindingItem.listItem.visibility = View.GONE
        }
        return SearchViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        if(filteredList.isNotEmpty())
        holder.bindingItem.activity = filteredList[position]
    }

    override fun getItemCount() = if(filteredList.isEmpty()) 1 else filteredList.size

    fun setList(activityList: List<ActivityUnit>?) {
        if (activityList != null) {
            activities = activityList
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(filteredList.isNotEmpty()) 1 else 0
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchText = constraint.toString().toLowerCase(Locale.ROOT)
                if (searchText.isNotEmpty()) {
                    val resultList: MutableList<ActivityUnit> = ArrayList()
                    for (activity in activities)
                        if (StringHelper.filterCondition(activity, searchText))
                            resultList.add(activity)
                    filteredList = resultList
                }else{
                    filteredList = emptyList()
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<ActivityUnit>
                notifyDataSetChanged()
            }
        }
    }

    class SearchViewHolder(val bindingItem: ItemUnitBinding) :
        RecyclerView.ViewHolder(bindingItem.root)
}