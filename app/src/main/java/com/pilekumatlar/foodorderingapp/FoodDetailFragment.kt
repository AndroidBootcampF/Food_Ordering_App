package com.pilekumatlar.foodorderingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pilekumatlar.foodorderingapp.databinding.FragmentFoodDetailBinding

class FoodDetailFragment : Fragment() {

    private var _binding: FragmentFoodDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonFoodAddCart.setOnClickListener {
                Toast.makeText(context, "Sepete eklendi.", Toast.LENGTH_SHORT).show()
            }
            imageButtonFoodShare.setOnClickListener {
                Toast.makeText(context, "Paylasildi.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}