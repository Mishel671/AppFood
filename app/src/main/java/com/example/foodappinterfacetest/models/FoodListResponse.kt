package com.example.foodappinterfacetest.models

import com.google.gson.annotations.SerializedName

data class FoodListResponse(
    @SerializedName("hints")
    val hints: List<HintListItem>)

data class HintListItem(
    @SerializedName("food")
    val food: FoodListItem)

data class FoodListItem(
    @SerializedName("foodId")
    val foodId: String,

    @SerializedName("label")
    val name: String,

    @SerializedName("nutrients")
    val nutrients: NutrientsListItem,

    @SerializedName("category")
    val category: String,

    val image: String)

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
    val fiber: Float
) //Клетчатка