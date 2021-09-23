package com.example.foodappinterfacetest.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class Nutrients(
    @SerializedName("ENERC_KCAL")
    val energy: Float = 0f,
    @SerializedName("PROCNT")
    val protein: Float = 0f, //Протеины
    @SerializedName("FAT")
    val fat: Float = 0f, //Жиры
    @SerializedName("CHOCDF")
    val carbs: Float = 0f, //Углеводы
    @SerializedName("FIBTG")
    val fiber: Float = 0f)//Клетчатка


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
