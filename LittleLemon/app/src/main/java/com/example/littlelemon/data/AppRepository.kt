package com.example.littlelemon.data

import android.content.Context
import com.example.littlelemon.data.local.AppDatabase
import com.example.littlelemon.data.network.LittleLemonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRepository(
    context: Context,
    private val littleLemonApi: LittleLemonApi,
    private val appDatabase: AppDatabase = AppDatabase.getDatabase(context)
) {
    fun getMenuData() = flow {
        //get the items from the local
        var localItems = appDatabase.getMenuItemDao().getMenuItems()

        //if there are no items in the local db
        //then fetch from the network, cache it
        //and then return the cached data
        if (localItems.isEmpty()) {
            val networkItems = littleLemonApi.getMenuData()
            localItems = networkItems.menu.map { it.toLocal() }
            appDatabase.getMenuItemDao().insertMenuItems(localItems)
        }
        emit(localItems)
    }.flowOn(Dispatchers.IO)
}