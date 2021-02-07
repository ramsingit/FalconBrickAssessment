package com.falconbrick.helpers

import com.falconbrick.model.ActivityUnit
import java.util.*

class StringHelper {
    companion object {
        /** Modify conditions, if necessary **/
        fun filterCondition(activity: ActivityUnit, searchText: String): Boolean {
            return activity.unit.title.toLowerCase(Locale.ROOT).contains(searchText) ||
                    activity.activity.activityName.toLowerCase(Locale.ROOT).contains(searchText) ||
                    activity.activity.stepName.toLowerCase(Locale.ROOT).contains(searchText)
        }
    }
}