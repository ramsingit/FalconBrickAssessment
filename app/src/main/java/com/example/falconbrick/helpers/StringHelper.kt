package com.example.falconbrick.helpers

import com.example.falconbrick.model.ActivityUnit
import java.util.*

class StringHelper {
    companion object {
        fun filterCondition(activity: ActivityUnit, searchText: String): Boolean {
            return activity.unit.title.toLowerCase(Locale.ROOT).contains(searchText) ||
                    activity.activity.activityName.toLowerCase(Locale.ROOT).contains(searchText) ||
                    activity.activity.stepName.toLowerCase(Locale.ROOT).contains(searchText)
        }
    }
}