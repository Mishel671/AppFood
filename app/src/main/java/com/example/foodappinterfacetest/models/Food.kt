package com.example.foodappinterfacetest.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
class Food(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name")val name: String? = "",
    @ColumnInfo(name = "image")val image: String? = "",
    @ColumnInfo(name = "nutrients")val nutrients: Nutrients?)
