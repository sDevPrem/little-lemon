package com.example.littlelemon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.littlelemon.data.model.MenuItemLocal

@Dao
interface MenuItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItems(items: List<MenuItemLocal>)

    @Query("SELECT * FROM menu_items")
    suspend fun getMenuItems(): List<MenuItemLocal>

}