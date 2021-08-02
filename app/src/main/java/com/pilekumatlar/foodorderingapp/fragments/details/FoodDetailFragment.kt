package com.pilekumatlar.foodorderingapp.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.pilekumatlar.foodorderingapp.R
import com.pilekumatlar.foodorderingapp.databinding.FragmentFoodDetailBinding

class FoodDetailFragment : Fragment(R.layout.fragment_food_detail) {
    private var _binding: FragmentFoodDetailBinding? = null

    private val binding get() = _binding!!

    private val args: FoodDetailFragmentArgs by navArgs()

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
            textViewFoodName.text = args.name
            textViewFoodPrice.text = args.price
            textViewFoodDescription.text = args.description
            Glide.with(this@FoodDetailFragment).load(args.url).into(imageViewFood)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}