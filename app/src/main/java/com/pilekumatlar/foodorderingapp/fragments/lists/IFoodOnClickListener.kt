package com.pilekumatlar.foodorderingapp.fragments.lists

import com.pilekumatlar.foodorderingapp.models.FoodItem

interface IFoodOnClickListener {
    fun onClick(name: FoodItem)
}