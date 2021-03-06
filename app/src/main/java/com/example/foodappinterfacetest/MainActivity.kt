package com.example.foodappinterfacetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodappinterfacetest.databinding.ActivityMainBinding
import com.example.foodappinterfacetest.utils.ACTIVITY_FRAGMENT
import com.example.foodappinterfacetest.utils.APP_ACTIVITY
import com.example.foodappinterfacetest.utils.AppPreference
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
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
        AppPreference.getPreference(this)
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
                R.id.menuFragment -> {
                    if(ACTIVITY_FRAGMENT.equals("1")) {
                        navController.navigate(R.id.menuFragment, null, optionsIn)
                    } else if(ACTIVITY_FRAGMENT.equals("3") || ACTIVITY_FRAGMENT.equals("2")){
                        navController.navigate(R.id.menuFragment, null, optionsOut)
                    }
                }
                R.id.profileFragment -> {
                    if(ACTIVITY_FRAGMENT.equals("1")) {
                        navController.navigate(R.id.profileFragment, null, optionsIn)

                    } else if(ACTIVITY_FRAGMENT.equals("3")) {
                        navController.navigate(R.id.profileFragment, null, optionsOut)
                    }

                }
                R.id.cartFragment -> {
                    navController.navigate(R.id.cartFragment,null,optionsIn)

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