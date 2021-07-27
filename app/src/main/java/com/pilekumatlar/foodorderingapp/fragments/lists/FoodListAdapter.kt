package com.pilekumatlar.foodorderingapp.fragments.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pilekumatlar.foodorderingapp.databinding.ItemFoodBinding
import com.pilekumatlar.foodorderingapp.databinding.ItemRestaurantsBinding
import com.pilekumatlar.foodorderingapp.models.FoodItem
import com.pilekumatlar.foodorderingapp.models.restaurants

class FoodListAdapter : RecyclerView.Adapter<FoodListAdapter.ListViewHolder>() {

    private var listFood = ArrayList<FoodItem>()
    private var listener: IFoodOnClickListener? = null

    class ListViewHolder(val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodItem: FoodItem, listener: IFoodOnClickListener?) {
            binding.textName.text = foodItem.name
            binding.textPrice.text = foodItem.price
            binding.itemFoodCardView.setOnClickListener { listener?.onClick(foodItem) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemFoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var item = this.listFood[position]
        holder.bind(item, listener)

    }

    override fun getItemCount(): Int = listFood.size

    fun setFoodData(list: ArrayList<FoodItem>) {
        this.listFood = list
        notifyDataSetChanged()
    }

    fun setFoodOnClickListener(listener: IFoodOnClickListener) {
        this.listener = listener
    }

}