package com.pilekumatlar.foodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pilekumatlar.foodorderingapp.addDialog.AddFoodFragment
import com.pilekumatlar.foodorderingapp.addDialog.IAddFoodListener
import com.pilekumatlar.foodorderingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

}

