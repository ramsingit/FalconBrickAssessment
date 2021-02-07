package com.falconbrick.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.falconbrick.R
import com.falconbrick.databinding.ItemUnitBinding
import com.falconbrick.helpers.StringHelper
import com.falconbrick.model.ActivityUnit
import java.util.*
import kotlin.collections.ArrayList

class SearchRecyclerAdapter : RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>(),
    Filterable {

    private lateinit var activities: List<ActivityUnit>
    private var filteredList: List<ActivityUnit> = emptyList()

    private val emptyListView = 0
    private val listView = 1

    /** Inflates recyclerview item layout using dataBinding and instantiates ViewHolder **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val bindingItem =
            ItemUnitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        if (viewType == listView) {
            bindingItem.listItem.visibility = View.VISIBLE
            bindingItem.emptyText.visibility = View.GONE
        } else {
            bindingItem.emptyText.visibility = View.VISIBLE
            bindingItem.listItem.visibility = View.GONE
            bindingItem.emptyText.text = parent.context.resources.getString(R.string.no_match)
        }
        return SearchViewHolder(bindingItem)
    }

    /** Binds the data to the recycler item **/
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        if (filteredList.isNotEmpty())
            holder.bindingItem.activity = filteredList[position]
    }

    /**
     * Return 1 even if list is empty inorder to display emptyListView ViewType Item
     */
    override fun getItemCount() = if (filteredList.isEmpty()) 1 else filteredList.size

    /** Sets the dataList for the adapter **/
    fun setList(activityList: List<ActivityUnit>?) {
        if (activityList != null) {
            activities = activityList
        }
    }

    /** Decides which ViewType to be shown **/
    override fun getItemViewType(position: Int): Int {
        return if (filteredList.isNotEmpty()) listView else emptyListView
    }

    /**
     * Custom filter to search the terms in the list
     */
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
                } else {
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