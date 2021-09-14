package com.example.foodappinterfacetest.screens.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappinterfacetest.R
import com.example.foodappinterfacetest.models.Food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item.view.*

class MenuVerticalAdapter : RecyclerView.Adapter<MenuVerticalAdapter.MyViewHolder>(){

    private var data: List<Food>? = null
    fun setListData(data: List<Food>?) {
        this.data = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuVerticalAdapter.MyViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuVerticalAdapter.MyViewHolder, position: Int) {
        holder.bind(data?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(data == null )return 0
        return data?.size!!
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageThumb = view.imageFood
        val tvTitle = view.titleFood
        val tvEnergy = view.energy
        val tvProtein = view.protein
        val tvFat = view.fat
        val tvCarbs = view.carbs
        val tvFiber = view.fiber

        fun bind(data: Food) {
            tvTitle.text = data.name
            tvEnergy.text = data.nutrients?.energy
            tvProtein.text = data.nutrients?.protein
            tvFat.text = data.nutrients?.fat
            tvCarbs.text = data.nutrients?.carbs
            tvFiber.text = data.nutrients?.fiber

            Picasso.get()
                .load(data.image)
                .into(imageThumb)
        }
    }
}