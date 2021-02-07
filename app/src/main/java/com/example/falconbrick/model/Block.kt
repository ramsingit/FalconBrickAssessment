package com.example.falconbrick.model

import com.google.gson.annotations.SerializedName

data class Block(
    @SerializedName("block_name") val blockName: String,
    val units: List<Unit>) {
}