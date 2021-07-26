package com.pilekumatlar.foodorderingapp.fragments.addingfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.FragmentAddRestaurantBinding
import com.pilekumatlar.foodorderingapp.fragments.base.BaseFragment
import com.pilekumatlar.foodorderingapp.models.restaurants
import androidx.navigation.fragment.navArgs

class FragmentAddRestaurant : Fragment(R.layout.fragment_add_restaurant) {
    private val mFirestore = FirebaseFirestore.getInstance()
    private lateinit var binding: FragmentAddRestaurantBinding
    private lateinit var restaurantName: EditText
    private lateinit var restaurantLocation: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddRestaurantBinding.bind(view)
        initViews(view)
    }

    private fun initViews(view: View) {
        binding.btnAddRestaurant.setOnClickListener {
            restaurantName = view.findViewById(R.id.edt_txt_restaurant_name)
            restaurantLocation = view.findViewById(R.id.edt_txt_restaurant_address)
            var restaurantInformations = restaurants(
                restaurantName.text.toString(),
                restaurantLocation.text.toString()
            )
            Toast.makeText(
                context,
                restaurantInformations.restaurantName,
                Toast.LENGTH_SHORT
            ).show()
            mFirestore.collection("Restaurants")
                .document()
                .set(restaurantInformations, SetOptions.merge())
                .addOnCompleteListener {

                    Log.v("Restaurant", "Restaurant Ekleme Başarılı")
                }
                .addOnFailureListener {
                    Log.v("Restaurant", "Ekleme Başarısız")
                }
        }
    }


}