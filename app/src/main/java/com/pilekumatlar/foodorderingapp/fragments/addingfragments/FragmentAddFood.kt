package com.pilekumatlar.foodorderingapp.fragments.addingfragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.storage.FirebaseStorage
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.FragmentAddFoodItemBinding
import java.util.*

class FragmentAddFood : Fragment(R.layout.fragment_add_food_item) {
    private var _binding: FragmentAddFoodItemBinding? = null

    private val binding get() = _binding!!

    private val storage = FirebaseStorage.getInstance()

    private var pickedImage: Uri? = null

    private val REQUEST_CODE = 100

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
            val uuid = UUID.randomUUID()
            val imageName = "${uuid}.png"
            val reference = storage.reference
            val imageReference = reference.child("images").child(imageName)
            if (pickedImage != null) {
                imageReference.putFile(pickedImage!!).addOnSuccessListener {
                    val uploadedImageReference =
                        FirebaseStorage.getInstance().reference.child("images").child(imageName)
                    uploadedImageReference.downloadUrl.addOnSuccessListener {
                    }
                }
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
            if (data != null) {
                pickedImage = data.data
            }
        }
    }
}