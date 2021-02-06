package com.example.falconbrick.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.falconbrick.R
import com.example.falconbrick.adapter.SearchRecyclerAdapter
import com.example.falconbrick.databinding.ActivitySearchBinding
import com.example.falconbrick.model.Block
import com.example.falconbrick.model.UnitActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_search)

        val searchBinding: ActivitySearchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)

        val activities = loadDataFromFile()
        searchBinding.searchAdapter = SearchRecyclerAdapter(activities)
    }

    /** Function to parse data JSON file*/
    private fun loadDataFromFile(): List<UnitActivity> {
        val jsonData = resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        val blockType = object : TypeToken<List<Block>>() {}.type
        return flattenList(Gson().fromJson(jsonData, blockType))
    }

    private fun flattenList(blockList: List<Block>): List<UnitActivity> {
        val activities : MutableList<UnitActivity> = ArrayList()
        for (block in blockList){
            for (unit in block.units){
                for(activity in unit.activities){
                    activities.add(UnitActivity(unit, activity))
                }
            }
        }
        return activities
    }
}