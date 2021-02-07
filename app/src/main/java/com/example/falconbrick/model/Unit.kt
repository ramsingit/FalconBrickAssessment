package com.example.falconbrick.model

import com.google.gson.annotations.SerializedName

class Unit(
    val activities: List<Activity>,
    val apt: String,
    @SerializedName("block_id") val blockId: String,
    @SerializedName("block_name") val blockName: String,
    val floor: String,
    val id: String,
    @SerializedName("property_id") val propertyId: String,
    val title: String,
    @SerializedName("unit_type") val unitType: String,
) {
}