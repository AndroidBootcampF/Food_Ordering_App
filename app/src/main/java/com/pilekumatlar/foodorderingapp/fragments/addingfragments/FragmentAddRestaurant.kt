package com.pilekumatlar.foodorderingapp.fragments.addingfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.FragmentAddRestaurantBinding
import com.pilekumatlar.foodorderingapp.models.Restaurants

class FragmentAddRestaurant : Fragment(R.layout.fragment_add_restaurant) {
    private val mFirestore = FirebaseFirestore.getInstance()
    private lateinit var restaurantName: EditText
    private lateinit var restaurantLocation: EditText

    private var _binding: FragmentAddRestaurantBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        binding.btnAddRestaurant.setOnClickListener {
            restaurantName = view.findViewById(R.id.edt_txt_restaurant_name)
            restaurantLocation = view.findViewById(R.id.edt_txt_restaurant_address)
            val restaurantInformations = Restaurants(
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