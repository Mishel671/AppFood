package com.example.foodappinterfacetest.screens.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappinterfacetest.MainActivityViewModel
import com.example.foodappinterfacetest.R
import com.example.foodappinterfacetest.adapter.HorizontalRecyclerViewAdapter
import com.example.foodappinterfacetest.adapter.RecyclerViewAdapter
import com.example.foodappinterfacetest.databinding.FragmentHomeBinding
import com.example.foodappinterfacetest.utils.ACTIVITY_FRAGMENT
import com.example.foodappinterfacetest.utils.APP_ACTIVITY
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : Fragment() {

    private lateinit var recyclerAdapter : RecyclerViewAdapter
    private lateinit var horizontalRecyclerAdapter: HorizontalRecyclerViewAdapter
    lateinit var shimmerView: ShimmerFrameLayout
    lateinit var chipGroup: ChipGroup


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        ACTIVITY_FRAGMENT = "1"
        InitView(view)
        shimmerView = view!!.findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container)
        initViewModel(view)
        initViewModel()

        return view
    }

    private fun InitView(view: View){
        chipGroup = view.findViewById(R.id.cgOptions)
        val chipName: ArrayList<String> = arrayListOf("Pizza", "Burger", "Salad", "Coffee", "Tea")

        for(i in chipName){
            val chip = Chip(context)
            val drawable = context?.let {
                ChipDrawable.createFromAttributes(
                    it, null, 0,
                    R.style.Widget_MaterialComponents_Chip_Choice)
            }
            if (drawable != null) {
                chip.setChipDrawable(drawable)
            }
            chip.setPadding(10,10,10,10)
            chip.setText(i)
            chip.setChipBackgroundColor(getResources().getColorStateList(R.drawable.chip_selection))
            chipGroup.addView(chip)
        }
    }

    private fun initViewModel(view : View){
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontalRecyclerView)
        horizontalRecyclerAdapter = HorizontalRecyclerViewAdapter()
        horizontalRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        horizontalRecyclerAdapter = HorizontalRecyclerViewAdapter()
        horizontalRecyclerView.adapter = horizontalRecyclerAdapter



        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter

    }

    private fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.recyclerListLiveData.observe(viewLifecycleOwner, {
            if(it !=null){
                shimmerView.stopShimmer()
                shimmerView.visibility = View.GONE
                recyclerAdapter.setUpdatedData(it)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}