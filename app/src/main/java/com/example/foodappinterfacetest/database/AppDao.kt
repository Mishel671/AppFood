package com.example.foodappinterfacetest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodappinterfacetest.models.Food

@Dao
interface AppDao {

    @Query("SELECT * FROM food")
    fun getAllRecords(): LiveData<List<Food>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(foodListItem: Food)

    @Query("DELETE FROM food")
    fun deleteAllRecords()
}