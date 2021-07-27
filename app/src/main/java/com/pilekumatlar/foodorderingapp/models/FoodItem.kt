package com.pilekumatlar.foodorderingapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodItem(
    var name: String = "",
    var description: String = "",
    var price: String = "",
    var id: String = ""
) : Parcelable