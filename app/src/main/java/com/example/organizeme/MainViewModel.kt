package com.example.organizeme

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val firstRun: MutableLiveData<Boolean> = MutableLiveData()

    private val firstRunKey = "FIRST_RUN_KEY"

    fun navigate(sharedPreferences: SharedPreferences) {
        if (sharedPreferences.getBoolean(firstRunKey, true)) {
            firstRun.value = true
            sharedPreferences.edit().putBoolean(firstRunKey, false).apply()
        } else {
            firstRun.value = false
        }
    }

}