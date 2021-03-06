package com.example.foodappinterfacetest.screens.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodappinterfacetest.R
import com.example.foodappinterfacetest.utils.ACTIVITY_FRAGMENT
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ACTIVITY_FRAGMENT = "2"
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}