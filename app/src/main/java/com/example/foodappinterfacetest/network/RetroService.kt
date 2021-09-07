package com.example.foodappinterfacetest.network

import com.example.foodappinterfacetest.models.FoodListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("/api/food-database/v2/parser")
    suspend fun getFoodList(@Query("app_id") app_id: String,
                            @Query("app_key") app_key: String,
                            @Query("ingr") name: String,
                            @Query("nutrition-type") nutritionType: String): FoodListResponse
}