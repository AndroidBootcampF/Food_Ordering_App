package com.pilekumatlar.foodorderingapp

import com.google.gson.annotations.SerializedName

data class FoodDetail(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)