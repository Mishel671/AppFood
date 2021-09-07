package com.example.foodappinterfacetest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappinterfacetest.models.FoodListItem
import com.example.foodappinterfacetest.network.RetroInstance
import com.example.foodappinterfacetest.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    val recyclerListLiveData = MutableLiveData<List<FoodListItem>>()

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO){
            val retroInstance =  RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getFoodList("82c1a9e7",
                "a01aea996fd4b505cfe74d0563ee77d2",
                "Pizza",
                "cooking",)
            val responseList = response.hints.map{it.food}
            recyclerListLiveData.postValue(responseList)
        }
    }
}