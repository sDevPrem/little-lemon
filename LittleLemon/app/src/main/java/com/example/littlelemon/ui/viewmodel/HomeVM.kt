package com.example.littlelemon.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.AppRepository
import com.example.littlelemon.data.model.MenuItemLocal
import com.example.littlelemon.data.model.util.Result
import com.example.littlelemon.data.model.util.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeVM(application: Application) : AndroidViewModel(application = application) {
    private val appRepository = AppRepository.getInstance(
        application.applicationContext
    )

    private val _menuData = MutableStateFlow<Result<List<MenuItemLocal>>>(Result.Loading)
    val menuData: StateFlow<Result<List<MenuItemLocal>>> = _menuData

    init {
        fetchData()
    }

    fun fetchData() {
        appRepository.getMenuData()
            .asResult()
            .onEach { _menuData.value = it }
            .launchIn(viewModelScope)
    }

}