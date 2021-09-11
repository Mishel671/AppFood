package com.example.foodappinterfacetest.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappinterfacetest.R
import com.example.foodappinterfacetest.models.FoodListItem
import com.example.foodappinterfacetest.utils.APP_ACTIVITY
import com.squareup.picasso.Picasso

class MenuVerticalAdapter : RecyclerView.Adapter<MenuVerticalAdapter.MyViewHolder>(){

    var items = emptyList<FoodListItem>()

    fun setUpdatedData(items: List<FoodListItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageThumb = view.findViewById<ImageView>(R.id.imageFood)
        val tvTitle = view.findViewById<TextView>(R.id.titleFood)
        val tvEnergy = view.findViewById<TextView>(R.id.energy)
        val tvProtein = view.findViewById<TextView>(R.id.protein)
        val tvFat = view.findViewById<TextView>(R.id.fat)
        val tvCarbs = view.findViewById<TextView>(R.id.carbs)
        val tvFiber = view.findViewById<TextView>(R.id.fiber)

        fun bind(data : FoodListItem) {
            tvTitle.text = data.name
            tvEnergy.text = APP_ACTIVITY.getString(R.string.energy_text) + data.nutrients.energy.toString() + APP_ACTIVITY.getString(R.string.energy_unit)
            tvProtein.text = APP_ACTIVITY.getString(R.string.protein_text) + data.nutrients.protein.toString() + APP_ACTIVITY.getString(R.string.protein_unit)
            tvFat.text = APP_ACTIVITY.getString(R.string.fat_text) + data.nutrients.fat.toString() + APP_ACTIVITY.getString(R.string.fat_unit)
            tvCarbs.text = APP_ACTIVITY.getString(R.string.carbs_text) + data.nutrients.carbs.toString() + APP_ACTIVITY.getString(R.string.carbs_unit)
            tvFiber.text = APP_ACTIVITY.getString(R.string.fiber_text) + data.nutrients.fiber.toString() + APP_ACTIVITY.getString(R.string.fiber_unit)

            val url  = data.image

            Picasso.get()
                .load(url)
                .into(imageThumb)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}