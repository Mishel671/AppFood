package com.example.foodappinterfacetest.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class Nutrients(
    @SerializedName("ENERC_KCAL")
    val energy: String? = "",
    @SerializedName("PROCNT")
    val protein: String? = "", //Протеины
    @SerializedName("FAT")
    val fat: String? = "", //Жиры
    @SerializedName("CHOCDF")
    val carbs: String? = "", //Углеводы
    @SerializedName("FIBTG")
    val fiber: String? = "")//Клетчатка


class TypeConverterNutrients{
    val gson : Gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?) :Nutrients? {
        if(data == null)return null
        val listType: Type = object : TypeToken<Nutrients?>() {}.type
        return gson.fromJson<Nutrients?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someobject: Nutrients?): String? {
        return gson.toJson(someobject)
    }
}
