package com.example.organizeme

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val firstRun: MutableLiveData<Boolean> = MutableLiveData()

    private val firstRunKey = "FIRST_RUN_KEY"
    private val registrationActiveKey = "REGISTRATION_ACTIVE_KEY"

    fun navigate(sharedPreferences: SharedPreferences) {
        if (sharedPreferences.getBoolean(firstRunKey, true)) {
            sharedPreferences.edit().putBoolean(registrationActiveKey, true).apply()
            firstRun.value = true
            sharedPreferences.edit().putBoolean(firstRunKey, false).apply()
        } else {
            sharedPreferences.edit().putBoolean(registrationActiveKey, false).apply()
            firstRun.value = false
        }
    }

}