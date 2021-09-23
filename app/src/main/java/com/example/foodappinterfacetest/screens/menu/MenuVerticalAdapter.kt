package com.example.foodappinterfacetest.screens.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappinterfacetest.R
import com.example.foodappinterfacetest.models.Food
import com.example.foodappinterfacetest.utils.APP_ACTIVITY
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item.view.*

class MenuVerticalAdapter : RecyclerView.Adapter<MenuVerticalAdapter.MyViewHolder>(){


    private lateinit var menuFragment: MenuFragment
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
            tvTitle.text = data.label
            tvEnergy.text = APP_ACTIVITY.getString(R.string.energy_text) + formatData(data.nutrients?.energy) + APP_ACTIVITY.getString(R.string.energy_unit)
            tvProtein.text = APP_ACTIVITY.getString(R.string.protein_text) + formatData(data.nutrients?.protein) + APP_ACTIVITY.getString(R.string.protein_unit)
            tvFat.text = APP_ACTIVITY.getString(R.string.fat_text) + formatData(data.nutrients?.fat) + APP_ACTIVITY.getString(R.string.fat_unit)
            tvCarbs.text = APP_ACTIVITY.getString(R.string.carbs_text) + formatData(data.nutrients?.carbs) + APP_ACTIVITY.getString(R.string.carbs_unit)
            tvFiber.text = APP_ACTIVITY.getString(R.string.fiber_text) + formatData(data.nutrients?.fiber) + APP_ACTIVITY.getString(R.string.fiber_unit)

            Picasso.get()
                .load(data.image)
                .into(imageThumb)
        }

        fun formatData(value: Float?):String {
            val formatValue = String.format("%.1f", value)
            return formatValue
        }
    }
}