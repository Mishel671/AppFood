package com.example.foodappinterfacetest.network

import com.example.foodappinterfacetest.models.FoodListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("/api/food-database/v2/parser")
    fun getDataFromAPI(
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String,
        @Query("ingr") query: String,
        @Query("nutrition-type") nutritionType: String): Call<FoodListResponse>
}