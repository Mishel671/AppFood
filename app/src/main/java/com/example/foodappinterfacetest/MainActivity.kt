package com.example.foodappinterfacetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodappinterfacetest.databinding.ActivityMainBinding
import com.example.foodappinterfacetest.screens.home.HomeFragment
import com.example.foodappinterfacetest.utils.ACTIVITY_FRAGMENT
import com.example.foodappinterfacetest.utils.APP_ACTIVITY
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigation: BottomNavigationView
    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        bottomNavigation = mBinding.bottomNavigationView
        APP_ACTIVITY = this
        navController = findNavController(R.id.fragment)
        bottomNavigation.setupWithNavController(navController)
        bottomNavAnim()

    }

    private fun bottomNavAnim() {

        val optionsIn = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.slide_from_right_to_left_in)
            .setExitAnim(R.anim.slide_from_right_to_left_out)
            .setPopUpTo(navController.graph.startDestination, false)
            .build()
        val optionsOut = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.slide_back_in)
            .setExitAnim(R.anim.slide_back_out)
            .setPopUpTo(navController.graph.startDestination, false)
            .build()

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> {
                    if(ACTIVITY_FRAGMENT.equals("1")) {
                        navController.navigate(R.id.homeFragment, null, optionsIn)
                    } else if(ACTIVITY_FRAGMENT.equals("3") || ACTIVITY_FRAGMENT.equals("2")){
                        navController.navigate(R.id.homeFragment, null, optionsOut)
                    }


                }
                R.id.profileFragment -> {
                    if(ACTIVITY_FRAGMENT.equals("1")) {
                        navController.navigate(R.id.profileFragment, null, optionsIn)

                    } else if(ACTIVITY_FRAGMENT.equals("3")) {
                        navController.navigate(R.id.profileFragment, null, optionsOut)
                    }
                }
                R.id.settingsFragment -> {
                    navController.navigate(R.id.settingsFragment,null,optionsIn)

                }
            }
            true
        }
        bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            return@setOnNavigationItemReselectedListener}
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}