package com.example.foodappinterfacetest.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.marginStart
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappinterfacetest.MainActivityViewModel
import com.example.foodappinterfacetest.R
import com.example.foodappinterfacetest.adapter.HorizontalRecyclerViewAdapter
import com.example.foodappinterfacetest.adapter.RecyclerViewAdapter
import com.example.foodappinterfacetest.databinding.FragmentHomeBinding
import com.example.foodappinterfacetest.utils.ACTIVITY_FRAGMENT
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type

class HomeFragment : Fragment() {

    private lateinit var recyclerAdapter : RecyclerViewAdapter
    private lateinit var horizontalRecyclerAdapter: HorizontalRecyclerViewAdapter
    lateinit var chip:Chip
    lateinit var shimmerView: ShimmerFrameLayout
    lateinit var chipGroup: ChipGroup
    private var _binding: FragmentHomeBinding? = null
    private val mBinding get() = _binding!!


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
        chipGroup = view.findViewById(R.id.chipGroup)
        val chipName: ArrayList<String> = arrayListOf("Pizza", "Burger", "Salad", "Coffee", "Tea")

        for(i in chipName){
            chip = Chip(context)
            val drawable = context?.let {
                ChipDrawable.createFromAttributes(
                    it, null, 0,
                    R.style.Widget_MaterialComponents_Chip_Choice)
            }
            if (drawable != null) {
                chip.setChipDrawable(drawable)
            }
            chip.setText(i)
            chip.chipCornerRadius = 25F
            chip.setChipBackgroundColor(getResources().getColorStateList(R.drawable.chip_selection))
            chip.elevation = 1F
            chipGroup.addView(chip)
        }
        chip.setOnClickListener{

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