package com.example.littlelemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.AppRepository
import com.example.littlelemon.data.model.util.Result
import com.example.littlelemon.data.model.util.asResult
import com.example.littlelemon.data.network.LittleLemonApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HomeVM : ViewModel() {
    private val appRepository = AppRepository(LittleLemonApi())
    val menuData = appRepository.getMenuData()
        .asResult()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            Result.Loading
        )
}