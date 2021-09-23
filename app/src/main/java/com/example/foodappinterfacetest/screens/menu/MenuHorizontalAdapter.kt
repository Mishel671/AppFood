package com.example.foodappinterfacetest.screens.menu


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappinterfacetest.R

class MenuHorizontalAdapter: RecyclerView.Adapter<MenuHorizontalAdapter.MyViewHolder>(){

    private val inflater: LayoutInflater? = null
    private val ads: List<Int> = listOf(R.drawable.ad_first, R.drawable.ad_second, R.drawable.ad_first, R.drawable.ad_second)



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
    : MenuHorizontalAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ad_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.adImageView?.setImageResource(ads[position])
    }

    override fun getItemCount(): Int {
        return ads.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var adImageView: ImageView? = null

        init{
            adImageView = itemView.findViewById(R.id.adImg)
        }
    }
}