package com.example.foodappinterfacetest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappinterfacetest.R

class HorizontalRecyclerViewAdapter: RecyclerView.Adapter<HorizontalRecyclerViewAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ad_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 2
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val adImage = view.findViewById<ImageView>(R.id.adImg)

    }
}