package com.example.falconbrick.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.falconbrick.model.Block
import com.example.falconbrick.model.ActivityUnit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchViewModel : ViewModel() {
    private var activitiesList: MutableLiveData<List<ActivityUnit>> = MutableLiveData()

    fun getActivitiesList(jsonData: String): MutableLiveData<List<ActivityUnit>> {
        activitiesList.value = getListData(jsonData)
        return activitiesList
    }

    /** Function to parse data JSON file and get the activitiesList*/
    private fun getListData(jsonData: String): List<ActivityUnit> {
        val blockType = object : TypeToken<List<Block>>() {}.type
        return flattenList(Gson().fromJson(jsonData, blockType))
    }

    private fun flattenList(blockList: List<Block>): List<ActivityUnit> {
        val activities: MutableList<ActivityUnit> = ArrayList()
        for (block in blockList) {
            for (unit in block.units) {
                for (activity in unit.activities) {
                    activities.add(ActivityUnit(unit, activity))
                }
            }
        }
        return activities
    }
}