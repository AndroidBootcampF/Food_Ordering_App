package com.pilekumatlar.foodorderingapp.fragments.lists

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.FragmentListRestaurantBinding
import com.pilekumatlar.foodorderingapp.models.restaurants

class FragmentListRestaurant : Fragment(R.layout.fragment_list_restaurant) {

    lateinit var restaurantRecyclerView: RecyclerView
    lateinit var addRestaurantButton:Button

    private val adapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        getDataFromFirebase()

    }

    private fun getDataFromFirebase() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Restaurants")
            .get()
            .addOnSuccessListener {
                val listTwo = ArrayList<restaurants>()
                for (i in it.documents) {
                    val restaurantInformations = i.toObject(restaurants::class.java)!!
                    if (restaurantInformations != null) {
                        restaurantInformations.id = i.id
                    }
                    listTwo.add(
                        restaurants(
                            restaurantInformations.restaurantName,
                            restaurantInformations.restaurantLocation
                        )
                    )
                }
                adapter.setRestaurantDataTwo(listTwo)
            }.addOnFailureListener {
                Log.v("Hata", "Hata Alındı")
            }
    }


    private fun initViews(view: View) {
        addRestaurantButton=view.findViewById(R.id.addRestaurantButton)
        restaurantRecyclerView = view.findViewById(R.id.restaurantsRecyclerView)
        restaurantRecyclerView.layoutManager = LinearLayoutManager(context)
        restaurantRecyclerView.adapter = adapter
        adapter.setRestaurantOnClickListener(object : IRestaurantOnClickListener {
            override fun onClick(restaurants: restaurants) {
                Toast.makeText(context, "${restaurants.restaurantName}", Toast.LENGTH_SHORT).show()
                val action =
                    FragmentListRestaurantDirections.actionFragmentListRestaurantToRestaurantDetailFragment(
                        restaurants.restaurantName,
                        restaurants.restaurantLocation

                    )
                findNavController().navigate(action)
            }

        })

        addRestaurantButton.setOnClickListener {
            val action =
                FragmentListRestaurantDirections.actionFragmentListRestaurantToFragmentAddRestaurant(

                )
            findNavController().navigate(action)
        }

    }


}

