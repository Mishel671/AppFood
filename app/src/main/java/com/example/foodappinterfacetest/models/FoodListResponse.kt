package com.example.foodappinterfacetest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FoodListResponse(
    @SerializedName("hints")
    val hints: List<HintListItem>) : Serializable

data class HintListItem(
    @SerializedName("food")
    val food: FoodListItem) : Serializable

data class FoodListItem(
    @SerializedName("foodId")
    val foodId: String,

    @SerializedName("label")
    val name: String,

    @SerializedName("nutrients")
    val nutrients: NutrientsListItem,

    @SerializedName("category")
    val category: String,

    val image: String) : Serializable

data class NutrientsListItem(
    @SerializedName("ENERC_KCAL")
    val energy: Float,

    @SerializedName("PROCNT")
    val protein: Float, //Протеины

    @SerializedName("FAT")
    val fat: Float, //Жиры

    @SerializedName("CHOCDF")
    val carbs: Float, //Углеводы

    @SerializedName("FIBTG")
    val fiber: Float //Клетчатка
) : Serializable