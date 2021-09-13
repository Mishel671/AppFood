package com.example.foodappinterfacetest.screens.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappinterfacetest.R
import com.example.foodappinterfacetest.databinding.FragmentMenuBinding
import com.example.foodappinterfacetest.utils.ACTIVITY_FRAGMENT
import com.example.foodappinterfacetest.utils.CATEGORY_KEY
import com.example.foodappinterfacetest.utils.QUERY
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
        initAdapters()
        initViewModel()
    }

    private fun initView(){
        mChipGroup = mBinding.chipGroup
        var count = 1
        val chipName: ArrayList<String> = arrayListOf("Pizza", "Nugget's", "Salad", "Coffee", "Tea")
        for(i in chipName){
            chip = Chip(context)
            val drawable = ChipDrawable.createFromAttributes(requireContext(), null, 0, R.style.My_Widget_MaterialComponents_Chip_Choice)
            chip.setChipDrawable(drawable)
            chip.setText(i)
            chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected)
            chip.setId(count)
            count++
            if(chip.text.equals(QUERY)){
                chip.isChecked = true
            }
            mChipGroup.addView(chip)
        }
        chip = mChipGroup.findViewById(CATEGORY_KEY)
        mChipGroup.setOnCheckedChangeListener(
            ChipGroup
                .OnCheckedChangeListener { group, checkedId ->
                    if(checkedId != -1){
                        recyclerVerticalAdapter.deleteData()
                        chip = mChipGroup.findViewById(checkedId)
                        setNewFood(checkedId)
                        initViewModel()
                    } else {
                        chip.isChecked = true
                    }
           })
    }

    fun setNewFood(chipId:Int){
        when (chipId){
            1 -> {
                CATEGORY_KEY = 1
                QUERY = "Pizza"
            }
            2 -> {
                CATEGORY_KEY = 2
                QUERY = "Nuggets"
            }
            3 ->{
                    CATEGORY_KEY = 3
                    QUERY = "Salad"
            }
            4 ->{
                    CATEGORY_KEY = 4
                    QUERY = "Coffee"
            }
            5 -> {
                CATEGORY_KEY = 5
                QUERY = "Tea"
            }
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
        shimmerView = mBinding.shimmerViewContainer
        mViewModel = ViewModelProvider(this).get(MenuFragmentViewModel::class.java)
        mViewModel.recyclerListLiveData.observe(viewLifecycleOwner, {
            if(it !=null){
                shimmerView.stopShimmer()
                shimmerView.visibility = View.GONE
                recyclerVerticalAdapter.setUpdatedData(it)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        mViewModel.makeApiCall(QUERY)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mRecyclerView.adapter = null
        mHorizontalRecyclerView.adapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MenuFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}