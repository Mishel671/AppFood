package com.example.foodappinterfacetest.network

import androidx.lifecycle.LiveData
import com.example.foodappinterfacetest.database.AppDao
import com.example.foodappinterfacetest.models.Food
import com.example.foodappinterfacetest.models.FoodListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val retroServiceInterface: RetroServiceInterface,
                                          private val appDao: AppDao
) {

    fun getAllRecords(): LiveData<List<Food>> {
        return appDao.getAllRecords()
    }

    fun insertRecord(food: Food) {
        appDao.insertRecords(food)
    }

    //get the data from github api...
    fun makeApiCall(id: String?, key: String?, query: String?, nutrionType: String?) {
        val call: Call<FoodListResponse> = retroServiceInterface.getDataFromAPI(id!!, key!!, query!!, nutrionType!!)
        call?.enqueue(object : Callback<FoodListResponse> {
            override fun onResponse(
                call: Call<FoodListResponse>,
                response: Response<FoodListResponse>
            ) {
                if(response.isSuccessful) {
                    appDao.deleteAllRecords()
                    response.body()?.hints?.map{it.food}?.forEach {
                        insertRecord(it)
                    }
                }
            }

            override fun onFailure(call: Call<FoodListResponse>, t: Throwable) {
                //
            }
        })
    }
}