package com.example.foodappinterfacetest.screens.menu

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappinterfacetest.R
import com.example.foodappinterfacetest.databinding.FragmentMenuBinding
import com.example.foodappinterfacetest.models.Food
import com.example.foodappinterfacetest.utils.ACTIVITY_FRAGMENT
import com.example.foodappinterfacetest.utils.APP_ACTIVITY
import com.example.foodappinterfacetest.utils.AppPreference
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_menu.*

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var verticalRecyclerAdapter : MenuVerticalAdapter
    private lateinit var horizontalRecyclerAdapter: MenuHorizontalAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mHorizontalRecyclerView: RecyclerView
    lateinit var mShimmerView: ShimmerFrameLayout
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
        mShimmerView = mBinding.shimmerViewContainer
        initView()
        initAdapters()
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
        chip.isChecked = true
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
            if(chip.text.equals(AppPreference.getQuery())){
                chip.isChecked = true
            }
            mChipGroup.addView(chip)
        }
        chip = mChipGroup.findViewById(AppPreference.getCategoryKey())
        mChipGroup.setOnCheckedChangeListener(
            ChipGroup
                .OnCheckedChangeListener { group, checkedId ->

                    if(isOnline(this@MenuFragment.context)) {
                        if (checkedId != -1) {
                            chip = mChipGroup.findViewById(checkedId)
                            setNewFood(checkedId)
                            initViewModel()
                        } else {
                            chip.isChecked = true
                        }
                    } else {
                        chip = mChipGroup.findViewById(AppPreference.getCategoryKey())
                        chip.isChecked = true
                        Toast.makeText(activity, "Internet connection required", Toast.LENGTH_SHORT).show()
                    }
           })
    }

    fun setNewFood(chipId:Int){
        when (chipId){
            1 -> {
                AppPreference.setCategoryKey(1)
                AppPreference.setQuery("Pizza")
            }
            2 -> {
                AppPreference.setCategoryKey(2)
                AppPreference.setQuery("Nuggets")
            }
            3 ->{
                AppPreference.setCategoryKey(3)
                AppPreference.setQuery("Salad")
            }
            4 ->{
                AppPreference.setCategoryKey(4)
                AppPreference.setQuery("Coffee")
            }
            5 -> {
                AppPreference.setCategoryKey(5)
                AppPreference.setQuery("Tea")
            }
        }
    }

    private fun initAdapters(){
        mHorizontalRecyclerView = mBinding.horizontalRecyclerView

        horizontalRecyclerAdapter = MenuHorizontalAdapter()
        mHorizontalRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        horizontalRecyclerAdapter = MenuHorizontalAdapter()
        mHorizontalRecyclerView.adapter = horizontalRecyclerAdapter


        mRecyclerView = mBinding.recyclerView
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MenuFragment.context)

            val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            verticalRecyclerAdapter = MenuVerticalAdapter()
            adapter = verticalRecyclerAdapter
        }
    }

    private fun initViewModel(){
        mViewModel = ViewModelProvider(this).get(MenuFragmentViewModel::class.java)
        mViewModel.getAllRepositoryList().observe(this, Observer<List<Food>> {
            if(it != null) {
                mShimmerView.stopShimmer()
                mShimmerView.visibility = View.GONE
                verticalRecyclerAdapter.setListData(it)
                verticalRecyclerAdapter.notifyDataSetChanged()

            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.makeApiCall()
    }

    fun isOnline(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
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