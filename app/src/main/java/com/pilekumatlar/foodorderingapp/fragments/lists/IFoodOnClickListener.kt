package com.pilekumatlar.foodorderingapp.fragments.lists

import com.pilekumatlar.foodorderingapp.models.FoodItem
import com.pilekumatlar.foodorderingapp.models.restaurants

interface IFoodOnClickListener {
    fun onClick(name: FoodItem)
}