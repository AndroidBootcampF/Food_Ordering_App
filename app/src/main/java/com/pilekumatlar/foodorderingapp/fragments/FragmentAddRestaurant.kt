package com.pilekumatlar.foodorderingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.FragmentAddRestaurantBinding
import com.pilekumatlar.foodorderingapp.models.restaurants

class FragmentAddRestaurant:Fragment(R.layout.fragment_add_restaurant) {
    private val mFirestore=FirebaseFirestore.getInstance()
    private lateinit var binding: FragmentAddRestaurantBinding
    private lateinit var restaurantName: EditText
    private lateinit var restaurantLocation:EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentAddRestaurantBinding.bind(view)
        initViews(view)
    }
    private fun initViews(view: View){
        var restaurantName=binding.edtTxtRestaurantName.text.toString()
        var restaurantLocation=binding.edtTxtRestaurantAddress.toString()
        var restaurantInformations=restaurants(restaurantName,restaurantLocation)
        val postHashMap=HashMap<String,Any>()
        postHashMap.put("restaurantName",restaurantName)
        postHashMap.put("restaurantLocation",restaurantLocation)

        binding.btnAddRestaurant.setOnClickListener {

            Toast.makeText(
                context,
                binding.edtTxtRestaurantName.text.toString(),
                Toast.LENGTH_SHORT
            ).show()
            mFirestore.collection("Restaurants")
                .add(postHashMap)
                .addOnCompleteListener {

                    Log.v("Restaurant","Restaurant Ekleme Başarılı")
                }
                .addOnFailureListener {
                    Log.v("Restaurant","Ekleme Başarısız")
                }
        }
    }


}