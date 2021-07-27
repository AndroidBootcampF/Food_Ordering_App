package com.pilekumatlar.foodorderingapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class restaurants(
    val restaurantName: String = "",
    val restaurantLocation: String = "",
    var id:String=""
) : Parcelable
