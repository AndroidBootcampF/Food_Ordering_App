package com.pilekumatlar.foodorderingapp.fragments.addingfragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.FragmentAddFoodItemBinding
import com.pilekumatlar.foodorderingapp.models.FoodItem

class FragmentAddFood : Fragment(R.layout.fragment_add_food_item) {
    private val args: FragmentAddFoodArgs by navArgs()
    private var _binding: FragmentAddFoodItemBinding? = null

    private val binding get() = _binding!!

    private val mFirestore = FirebaseFirestore.getInstance()

    val REQUEST_CODE = 100

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddFoodItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textAddFood.setOnClickListener {
            val foodName: String = binding.editTextFoodName.text.toString()
            val foodDescription: String = binding.editTextFoodDescription.text.toString()
            val foodPrice: String = binding.editTextFoodPrice.text.toString()
            val foodId: String = args.id.toString()

            val foodInformations = FoodItem(foodName, foodDescription, foodPrice, foodId)

            mFirestore.collection("Foods")
                .document()
                .set(foodInformations, SetOptions.merge())
                .addOnSuccessListener {
                    Toast.makeText(context, "Yemek Ekleme Başarılı", Toast.LENGTH_SHORT).show()
                    val action =
                        FragmentAddFoodDirections.actionFragmentAddFoodToFragmentListRestaurant()
                    findNavController().navigate(action)
                }
                .addOnFailureListener {
                    Log.v("Yemek", "Ekleme Başarısız")
                }
        }
        binding.buttonUploadImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            binding.imageView.setImageURI(data?.data) // handle chosen image
        }
    }
}