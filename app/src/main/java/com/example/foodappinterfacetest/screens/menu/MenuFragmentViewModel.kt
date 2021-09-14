package com.example.foodappinterfacetest.screens.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.foodappinterfacetest.models.Food
import com.example.foodappinterfacetest.network.RetroRepository
import javax.inject.Inject

class MenuFragmentViewModel @Inject constructor(private val repository: RetroRepository)
    : ViewModel() {

    fun getAllRepositoryList(): LiveData<List<Food>> {
        return repository.getAllRecords()
    }

    fun makeApiCall(){
        repository.makeApiCall("82c1a9e7",
            "a01aea996fd4b505cfe74d0563ee77d2",
            "Nuggets",
            "cooking")
    }
}