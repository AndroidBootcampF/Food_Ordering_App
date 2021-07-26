package com.pilekumatlar.foodorderingapp.addDialog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pilekumatlar.foodorderingapp.FoodItem
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.ActivityMainBinding
import com.pilekumatlar.foodorderingapp.databinding.AddFoodItemBinding

class AddFoodFragment : Fragment() {

    private var _binding: AddFoodItemBinding?= null

    private val binding get() = _binding!!


    private val mFirestore= FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AddFoodItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textAddFood.setOnClickListener {

            var foodName: String = binding.editTextFoodName.text.toString()
            var foodDescription: String = binding.editTextFoodDescription.text.toString()
            var foodPrice: String = binding.editTextFoodPrice.text.toString()

            val foodInformations = FoodItem(foodName,foodDescription,foodPrice.toInt())

            mFirestore.collection("Foods")
                .document()
                .set(foodInformations, SetOptions.merge())
                .addOnSuccessListener {
                    Log.v("Yemek","Yemek Ekleme Başarılı")
                }
                .addOnFailureListener {
                    Log.v("Yemek","Ekleme Başarısız")
                }
        }
    }

}