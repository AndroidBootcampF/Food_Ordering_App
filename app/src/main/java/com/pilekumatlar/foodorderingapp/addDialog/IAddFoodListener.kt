package com.pilekumatlar.foodorderingapp.addDialog

import com.pilekumatlar.foodorderingapp.FoodItem

interface IAddFoodListener {
    fun onAddButtonClicked(item : FoodItem)
}