package com.pilekumatlar.foodorderingapp.fragments.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pilekumatlar.foodorderingapp.databinding.ItemRestaurantsBinding
import com.pilekumatlar.foodorderingapp.models.restaurants

class ListAdapter:RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    //private var list=ArrayList<Restaurant>()
    private var listTwo=ArrayList<restaurants>()
    private var listener:IRestaurantOnClickListener?=null

    class ListViewHolder(val binding:ItemRestaurantsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(restaurants: restaurants,listener: IRestaurantOnClickListener?){
            binding.nameTextView.text=restaurants.restaurantName
            binding.locationTextView.text=restaurants.restaurantLocation
            binding.containerCardView.setOnClickListener { listener?.onClick(restaurants)}
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemRestaurantsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var item=this.listTwo[position]
        holder.bind(item,listener)
    }

    override fun getItemCount(): Int =listTwo.size



    fun setRestaurantDataTwo(list:ArrayList<restaurants>){
        this.listTwo=list
        notifyDataSetChanged()
    }
    fun setRestaurantOnClickListener(listener:IRestaurantOnClickListener){
        this.listener=listener
    }
}