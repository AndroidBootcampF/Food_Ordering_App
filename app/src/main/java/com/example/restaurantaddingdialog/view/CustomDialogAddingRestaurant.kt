package com.example.restaurantaddingdialog.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.example.restaurantaddingdialog.R

class CustomDialogAddingRestaurant(context: Context) : Dialog(context) {

    init {
        setCancelable(true)//destroy dialog screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog_add_restaurant)

    }

}