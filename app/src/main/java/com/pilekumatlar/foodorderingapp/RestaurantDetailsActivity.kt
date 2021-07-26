package com.pilekumatlar.foodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.pilekumatlar.foodorderingapp.adapters.FragmentAdapter
import com.pilekumatlar.foodorderingapp.fragments.Menu
import com.pilekumatlar.foodorderingapp.fragments.Orders
import com.pilekumatlar.foodorderingapp.fragments.RestaurantInfo

class RestaurantDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)


        var viewPager = findViewById(R.id.viewPager) as ViewPager
        var tablayout = findViewById(R.id.tablayout) as TabLayout

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(Menu(),"Menü")
        fragmentAdapter.addFragment(RestaurantInfo(),"Restoran Detayları")
        fragmentAdapter.addFragment(Orders(),"Önceki Siparişlerim")


        viewPager.adapter = fragmentAdapter
        tablayout.setupWithViewPager(viewPager)
    }
}