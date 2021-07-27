package com.pilekumatlar.foodorderingapp.fragments.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.FragmentRestaurantDetailBinding
import com.pilekumatlar.foodorderingapp.fragments.lists.*
import com.pilekumatlar.foodorderingapp.models.FoodItem
import com.pilekumatlar.foodorderingapp.models.Restaurants


class RestaurantDetailFragment : Fragment(R.layout.fragment_restaurant_detail) {
    private val args: RestaurantDetailFragmentArgs by navArgs()
    private lateinit var nameTextView: TextView
    private lateinit var locationTextView: TextView
    private val adapter_two = FoodListAdapter()
    lateinit var foodsRecyclerView: RecyclerView

    private var _binding: FragmentRestaurantDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        handleArgs()
        getDataFromFirebase()
        binding.buttonAddFood.setOnClickListener {
            Toast.makeText(context, getRestaurantId(), Toast.LENGTH_SHORT).show()
            val action =
                RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToFragmentAddFood(
                    getRestaurantId()
                )
            findNavController().navigate(action)
        }
    }

    private fun handleArgs() {
        args.name?.let {
            nameTextView.text = it
        }
        args.location?.let {
            locationTextView.text = it
        }
    }

    private fun initViews(view: View) {
        nameTextView = view.findViewById(R.id.detailNameTextView)
        locationTextView = view.findViewById(R.id.detailLocationTextView)
        foodsRecyclerView = view.findViewById(R.id.foodsRecyclerView)
        foodsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        foodsRecyclerView.adapter = adapter_two
        adapter_two.setFoodOnClickListener(object : IFoodOnClickListener {
            override fun onClick(foodItem: FoodItem) {
                Toast.makeText(context, "${foodItem.name}", Toast.LENGTH_SHORT).show()
                val action =
                    RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToFoodDetailFragment(
                        foodItem.name,
                        foodItem.price,
                        foodItem.description
                    )
                findNavController().navigate(action)
            }
        })
    }

    private fun getDataFromFirebase() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Foods")
            //.whereEqualTo("Id", getRestaurantId())
            .get()
            .addOnSuccessListener {
                val listTwo = ArrayList<FoodItem>()
                for (i in it.documents) {
                    val foodInformations = i.toObject(FoodItem::class.java)!!
                    Log.v("Foods", "${foodInformations.name}")
                    listTwo.add(
                        FoodItem(
                            foodInformations.name,
                            foodInformations.description,
                            foodInformations.price
                        )
                    )
                }
                adapter_two.setFoodData(listTwo)
            }.addOnFailureListener {
                Log.v("Hata", "Hata Alındı")
            }
    }

    fun getRestaurantId(): String {
        var id = "03eK2ONr1UrR0OJUkW6j"
        val db = FirebaseFirestore.getInstance()

        db.collection("Restaurants")
            .get()
            .addOnSuccessListener {
                for (i in it.documents) {
                    val restaurantInformations = i.toObject(Restaurants::class.java)!!
                    restaurantInformations.id = i.id
                    id = restaurantInformations.id
                    Log.v("IdRestaurant", "${i.id}")
                }
            }.addOnFailureListener {
                Log.v("Hata", "Hata Alındı")
            }
        return id
    }
}