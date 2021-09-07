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
import com.example.foodappinterfacetest.MainActivityViewModel
import com.example.foodappinterfacetest.R
import com.example.foodappinterfacetest.adapter.RecyclerViewAdapter
import com.facebook.shimmer.ShimmerFrameLayout

class HomeFragment : Fragment() {

    private lateinit var recyclerAdapter : RecyclerViewAdapter
    lateinit var shimmerView: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        shimmerView = view!!.findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container)

        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view : View){
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