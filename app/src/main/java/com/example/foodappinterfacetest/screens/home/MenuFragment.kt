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
import com.example.foodappinterfacetest.utils.*
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

class MenuFragment : Fragment() {

    private lateinit var recyclerVerticalAdapter : MenuVerticalAdapter
    private lateinit var mHorizontalRecyclerAdapter: MenuHorizontalAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mHorizontalRecyclerView: RecyclerView
    lateinit var shimmerView: ShimmerFrameLayout
    lateinit var chip:Chip
    private lateinit var mChipGroup: ChipGroup
    private var _binding: FragmentMenuBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel:MenuFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        ACTIVITY_FRAGMENT = "1"
        initView()
        shimmerView = mBinding.shimmerViewContainer
        initAdapters()
        initViewModel()
    }

    private fun initView(){
        mChipGroup = mBinding.chipGroup
        val chipName: ArrayList<String> = arrayListOf("Pizza", "Burger", "Salad", "Coffee", "Tea")
        for(i in chipName){
            chip = Chip(context)
            val drawable = ChipDrawable.createFromAttributes(requireContext(), null, 0, R.style.My_Widget_MaterialComponents_Chip_Choice)
            chip.setChipDrawable(drawable)
            chip.setText(i)
            chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected)
            if(chip.text.equals("Pizza")){
                chip.isChecked = true
            }
            mChipGroup.addView(chip)
        }
//        chip = mChipGroup.findViewById(CATEGORY_KEY)
//        chip.isChecked = true
        mChipGroup.setOnCheckedChangeListener(
            ChipGroup
                .OnCheckedChangeListener { group, checkedId ->
                if(checkedId != -1){
                    setNewFood(checkedId)
                    initViewModel()
                } else {
                    chip.isChecked = true
                }
                Toast.makeText(APP_ACTIVITY, "Chip is " + mChipGroup.checkedChipId, Toast.LENGTH_SHORT).show();
            })
    }

    fun setNewFood(chipId:Int){
        when (chipId){
            1 -> QUERY = "Pizza"
            2 -> QUERY = "Burger"
            3 -> QUERY = "Salad"
            4 -> QUERY = "Coffee"
            5 -> QUERY = "Tea"
        }
    }

    private fun initAdapters(){
        mHorizontalRecyclerView = mBinding.horizontalRecyclerView
        mHorizontalRecyclerAdapter = MenuHorizontalAdapter()
        mHorizontalRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        mHorizontalRecyclerAdapter = MenuHorizontalAdapter()
        mHorizontalRecyclerView.adapter = mHorizontalRecyclerAdapter



        mRecyclerView = mBinding.recyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerVerticalAdapter = MenuVerticalAdapter()
        mRecyclerView.adapter = recyclerVerticalAdapter

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
        if(QUERY.equals("")){
            viewModel.makeApiCall(DEFAULT_QUERY)
        } else {
            viewModel.makeApiCall(QUERY)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        _binding = null
//        mRecyclerView.adapter = null
//        mHorizontalRecyclerView.adapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MenuFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}