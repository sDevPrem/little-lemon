package com.example.littlelemon.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.PreferenceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileVM(application: Application) : AndroidViewModel(application = application) {
    private val preferenceRepository =
        PreferenceRepository.getPreferenceRepository(application.applicationContext)
    val user = preferenceRepository.getUser()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            null
        )

    fun logOut() {
        //The user should be deleted even if this view model is destroyed
        CoroutineScope(context = Dispatchers.IO).launch {
            preferenceRepository.clearUser()
        }
    }
}