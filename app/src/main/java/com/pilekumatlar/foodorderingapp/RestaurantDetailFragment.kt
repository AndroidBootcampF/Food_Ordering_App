package com.pilekumatlar.foodorderingapp

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.pilekumatlar.foodorderingapp.databinding.FragmentRestaurantDetailBinding
import com.pilekumatlar.foodorderingapp.fragments.lists.*
import com.pilekumatlar.foodorderingapp.models.FoodItem
import com.pilekumatlar.foodorderingapp.models.restaurants


class RestaurantDetailFragment : Fragment(R.layout.fragment_restaurant_detail) {
    private val args:RestaurantDetailFragmentArgs by navArgs()
    private lateinit var nameTextView: TextView
    private lateinit var locationTextView: TextView
    private val adapter_two=FoodListAdapter()
    lateinit var foodsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        handleArgs()
        getDataFromFirebase()
    }

    private fun handleArgs(){
        args.name?.let{
            nameTextView.text=it
        }
        args.location?.let{
          locationTextView.text=it
        }
    }
    private fun initViews(view: View){
        nameTextView=view.findViewById(R.id.detailNameTextView)
        locationTextView=view.findViewById(R.id.detailLocationTextView)
        foodsRecyclerView=view.findViewById(R.id.foodsRecyclerView)
        foodsRecyclerView.layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        foodsRecyclerView.adapter=adapter_two
        adapter_two.setFoodOnClickListener(object : IFoodOnClickListener {
            override fun onClick(foodItem: FoodItem) {
                Toast.makeText(context,"${foodItem.name}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getDataFromFirebase() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Foods")
            .get()
            .addOnSuccessListener {
                val listTwo = ArrayList<FoodItem>()
                for (i in it.documents) {
                    val foodInformations = i.toObject(FoodItem::class.java)!!
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
}