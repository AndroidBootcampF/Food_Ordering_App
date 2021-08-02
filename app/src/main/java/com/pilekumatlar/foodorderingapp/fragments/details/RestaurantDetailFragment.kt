package com.pilekumatlar.foodorderingapp.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.FragmentRestaurantDetailBinding
import com.pilekumatlar.foodorderingapp.fragments.lists.FoodListAdapter
import com.pilekumatlar.foodorderingapp.fragments.lists.IFoodOnClickListener
import com.pilekumatlar.foodorderingapp.models.FoodItem

class RestaurantDetailFragment : Fragment(R.layout.fragment_restaurant_detail) {
    private val args: RestaurantDetailFragmentArgs by navArgs()
    private lateinit var nameTextView: TextView
    private lateinit var locationTextView: TextView
    private val adapterTwo = FoodListAdapter()
    private lateinit var foodsRecyclerView: RecyclerView

    private var _binding: FragmentRestaurantDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
            Toast.makeText(context, args.id, Toast.LENGTH_SHORT).show()
            val action =
                RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToFragmentAddFood(
                    args.id
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
        foodsRecyclerView.adapter = adapterTwo
        adapterTwo.setFoodOnClickListener(object : IFoodOnClickListener {
            override fun onClick(name: FoodItem) {
                val action =
                    RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToFoodDetailFragment(
                        name.name,
                        name.price,
                        name.description,
                        name.imgUrl
                    )
                findNavController().navigate(action)
            }
        })
    }

    private fun getDataFromFirebase() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Foods")
            .whereEqualTo("id", args.id)
            .get()
            .addOnSuccessListener {
                val listTwo = ArrayList<FoodItem>()
                for (i in it.documents) {
                    val foodInformations = i.toObject(FoodItem::class.java)!!
                    listTwo.add(
                        FoodItem(
                            foodInformations.name,
                            foodInformations.description,
                            foodInformations.price,
                            foodInformations.id,
                            foodInformations.imgUrl
                        )
                    )
                }
                adapterTwo.setFoodData(listTwo)
            }.addOnFailureListener {
                Log.v("Hata", "Hata Alındı")
            }
    }
}
