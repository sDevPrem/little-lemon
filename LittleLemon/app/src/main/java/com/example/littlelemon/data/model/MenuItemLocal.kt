package com.example.littlelemon.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_items")
data class MenuItemLocal(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int = 0,
    @ColumnInfo("image")
    val image: String = "",
    @ColumnInfo("price")
    val price: String = "",
    @ColumnInfo("description")
    val description: String = "",
    @ColumnInfo("title")
    val title: String = "",
    @ColumnInfo("category")
    val category: String = ""

)