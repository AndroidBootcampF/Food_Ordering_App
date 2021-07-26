package com.pilekumatlar.foodorderingapp.fragments.lists

import com.pilekumatlar.foodorderingapp.models.restaurants


interface IRestaurantOnClickListener {
    fun onClick(name: restaurants)
}