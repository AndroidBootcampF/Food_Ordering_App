package com.pilekumatlar.foodorderingapp.fragments.lists

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.FragmentListRestaurantBinding
import com.pilekumatlar.foodorderingapp.models.restaurants

class FragmentListRestaurant: Fragment(R.layout.fragment_list_restaurant) {
    private lateinit var binding:FragmentListRestaurantBinding
    private val adapter=ListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentListRestaurantBinding.bind(view)
        initViews(view)
        getDataFromFirebase()
    }
   private fun getDataFromFirebase(){
        val db=FirebaseFirestore.getInstance()
        db.collection("Restaurants")
            .get()
            .addOnSuccessListener {
                val listTwo=ArrayList<restaurants>()
                for (i in it.documents){
                    val restaurantInformations=i.toObject(restaurants::class.java)!!
                    listTwo.add(restaurants(restaurantInformations.restaurantName,restaurantInformations.restaurantLocation))
                }
                adapter.setRestaurantDataTwo(listTwo)
            }.addOnFailureListener {
                Log.v("Hata","Hata Alındı")
            } }

    private fun initViews(view: View){

        binding.restaurantsRecyclerView.layoutManager= LinearLayoutManager(context)
        binding.restaurantsRecyclerView.adapter=adapter
        adapter.setRestaurantOnClickListener(object :IRestaurantOnClickListener{
            override fun onClick(restaurants: restaurants) {
                Toast.makeText(context,"${restaurants.restaurantName}",Toast.LENGTH_SHORT).show()

            }
        }) }

}

