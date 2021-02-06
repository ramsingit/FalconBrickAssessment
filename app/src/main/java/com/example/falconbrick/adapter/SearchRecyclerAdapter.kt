package com.example.falconbrick.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.falconbrick.databinding.ItemUnitBinding
import com.example.falconbrick.model.UnitActivity

class SearchRecyclerAdapter(private val activities: List<UnitActivity>) : RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val bindingItem = ItemUnitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindingItem.activity = activities[position]
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    class SearchViewHolder(val bindingItem: ItemUnitBinding) : RecyclerView.ViewHolder(bindingItem.root){
        val title: TextView = bindingItem.unitName
        val blockName: TextView = bindingItem.activityName
        val progress: TextView = bindingItem.progress
    }
}