package com.example.foodappinterfacetest.screens.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodappinterfacetest.R
import com.example.foodappinterfacetest.utils.ACTIVITY_FRAGMENT

class CartFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ACTIVITY_FRAGMENT = "3"
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


}