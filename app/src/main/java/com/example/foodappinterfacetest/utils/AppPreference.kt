package com.example.foodappinterfacetest.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreference {

    private const val QUERY = "query"
    private const val CATEGORY_KEY = "category_key"
    private const val NAME_PREF = "preference"
    private lateinit var mPreferences: SharedPreferences

    fun getPreference(context: Context):SharedPreferences{
        mPreferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return mPreferences
    }

    fun setQuery(query:String){
        mPreferences.edit()
            .putString(QUERY,query)
            .apply()
    }

    fun getQuery():String{
        return mPreferences.getString(QUERY, DEF_QUERY).toString()
    }

    fun setCategoryKey(key:Int){
        mPreferences.edit()
            .putInt(CATEGORY_KEY,key)
            .apply()
    }

    fun getCategoryKey():Int{
        return mPreferences.getInt(CATEGORY_KEY, DEF_CATEGORY_KEY)
    }
}