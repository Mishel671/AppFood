package com.example.foodappinterfacetest.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappinterfacetest.R
import com.example.foodappinterfacetest.databinding.FragmentMenuBinding
import com.example.foodappinterfacetest.utils.ACTIVITY_FRAGMENT
import com.example.foodappinterfacetest.utils.CATEGORY_KEY
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

class MenuFragment : Fragment() {

    private lateinit var recyclerVerticalAdapter : MenuVerticalAdapter
    private lateinit var menuHorizontalRecyclerAdapter: MenuHorizontalAdapter
    lateinit var shimmerView: ShimmerFrameLayout
    lateinit var chip:Chip
    lateinit var chipGroup: ChipGroup
    private var _binding: FragmentMenuBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel:MenuFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
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
            val drawable = ChipDrawable.createFromAttributes(requireContext(), null, 0, R.style.My_Widget_MaterialComponents_Chip_Choice)
            chip.setChipDrawable(drawable)
            chip.setText(i)
            chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected)
            chipGroup.addView(chip)
        }
        chip = chipGroup.findViewById(CATEGORY_KEY)
        chip.isChecked = true
        chipGroup.setOnCheckedChangeListener(
            ChipGroup.OnCheckedChangeListener { group, checkedId ->
                if(checkedId != -1){
                    chip = chipGroup.findViewById(checkedId)
                } else {
                    chip.isChecked = true
                }
            })
    }



    private fun initViewModel(view : View){
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontalRecyclerView)
        menuHorizontalRecyclerAdapter = MenuHorizontalAdapter()
        horizontalRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        menuHorizontalRecyclerAdapter = MenuHorizontalAdapter()
        horizontalRecyclerView.adapter = menuHorizontalRecyclerAdapter



        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerVerticalAdapter = MenuVerticalAdapter()
        recyclerView.adapter = recyclerVerticalAdapter

    }

    private fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(MenuFragmentViewModel::class.java)
        viewModel.recyclerListLiveData.observe(viewLifecycleOwner, {
            if(it !=null){
                shimmerView.stopShimmer()
                shimmerView.visibility = View.GONE
                recyclerVerticalAdapter.setUpdatedData(it)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MenuFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}