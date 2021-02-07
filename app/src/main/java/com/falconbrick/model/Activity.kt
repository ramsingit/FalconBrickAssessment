package com.falconbrick.model

import com.google.gson.annotations.SerializedName

class Activity(
    @SerializedName("activity_name") val activityName: String,
    @SerializedName("activity_status") val activityStatus: String,
    @SerializedName("current_user_name") val currentUsername: String,
    val id: String,
    @SerializedName("step_name") val stepName: String,
    val progress: String,
)