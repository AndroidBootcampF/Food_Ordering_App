package com.pilekumatlar.foodorderingapp.fragments.lists

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.FragmentListRestaurantBinding
import com.pilekumatlar.foodorderingapp.models.Restaurants

class FragmentListRestaurant : Fragment(R.layout.fragment_list_restaurant) {

    private var _binding: FragmentListRestaurantBinding? = null

    private val binding get() = _binding!!

    lateinit var restaurantRecyclerView: RecyclerView

    private val adapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        getDataFromFirebase()
        binding.addRestaurantButton.setOnClickListener {
            val action =
                FragmentListRestaurantDirections.actionFragmentListRestaurantToFragmentAddRestaurant()
            findNavController().navigate(action)
        }
    }

    private fun getDataFromFirebase() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Restaurants")
            .get()
            .addOnSuccessListener {
                val listTwo = ArrayList<Restaurants>()
                for (i in it.documents) {
                    val restaurantInformations = i.toObject(Restaurants::class.java)!!
                    restaurantInformations.id = i.id
                    listTwo.add(
                        Restaurants(
                            restaurantInformations.restaurantName,
                            restaurantInformations.restaurantLocation,
                            restaurantInformations.id
                        )
                    )
                }
                adapter.setRestaurantDataTwo(listTwo)
            }.addOnFailureListener {
                Log.v("Hata", "Hata Alındı")
            }
    }

    private fun initViews(view: View) {
        restaurantRecyclerView = view.findViewById(R.id.restaurantsRecyclerView)
        restaurantRecyclerView.layoutManager = LinearLayoutManager(context)
        restaurantRecyclerView.adapter = adapter
        adapter.setRestaurantOnClickListener(object : IRestaurantOnClickListener {
            override fun onClick(restaurants: Restaurants) {

                //Toast.makeText(context, "$restaurants", Toast.LENGTH_SHORT).show()
                val action =
                    FragmentListRestaurantDirections.actionFragmentListRestaurantToRestaurantDetailFragment(
                        restaurants.restaurantName,
                        restaurants.restaurantLocation,
                        restaurants.id
                    )
                findNavController().navigate(action)
            }
        })
    }
}
