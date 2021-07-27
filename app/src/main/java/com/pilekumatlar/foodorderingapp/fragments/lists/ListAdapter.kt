package com.pilekumatlar.foodorderingapp.fragments.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pilekumatlar.foodorderingapp.databinding.ItemRestaurantsBinding
import com.pilekumatlar.foodorderingapp.models.Restaurants

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    private var listTwo = ArrayList<Restaurants>()

    private var listener: IRestaurantOnClickListener? = null

    class ListViewHolder(val binding: ItemRestaurantsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(Restaurants: Restaurants, listener: IRestaurantOnClickListener?) {
            binding.nameTextView.text = Restaurants.restaurantName
            binding.locationTextView.text = Restaurants.restaurantLocation
            binding.containerCardView.setOnClickListener { listener?.onClick(Restaurants) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemRestaurantsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = this.listTwo[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = listTwo.size

    fun setRestaurantDataTwo(list: ArrayList<Restaurants>) {
        this.listTwo = list
        notifyDataSetChanged()
    }

    fun setRestaurantOnClickListener(listener: IRestaurantOnClickListener) {
        this.listener = listener
    }
}