package com.example.littlelemon.data

import android.content.Context
import com.example.littlelemon.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PreferenceRepository(
    private val context: Context
) {
    companion object {
        const val USER_PREFERENCE_FILE_KEY = "user_preference_file"

        const val USER_FIRST_NAME = "user_first_name"
        const val USER_LAST_NAME = "user_second_name"
        const val USER_EMAIL = "user_email"
    }

    suspend fun saveUser(user: User): Boolean = withContext(Dispatchers.IO) {
        val sharedPref =
            context.getSharedPreferences(USER_PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
                ?: return@withContext false
        with(sharedPref.edit()) {
            putString(USER_FIRST_NAME, user.firstName)
            putString(USER_LAST_NAME, user.lastName)
            putString(USER_EMAIL, user.email)
            return@withContext commit()
        }
    }
}