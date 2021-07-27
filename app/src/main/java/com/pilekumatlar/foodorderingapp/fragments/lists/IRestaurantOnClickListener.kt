package com.pilekumatlar.foodorderingapp.fragments.lists

import com.pilekumatlar.foodorderingapp.models.Restaurants

interface IRestaurantOnClickListener {
    fun onClick(name: Restaurants)
}