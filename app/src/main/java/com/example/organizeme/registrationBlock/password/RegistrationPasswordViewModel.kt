package com.example.organizeme.registrationBlock.password

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organizeme.R

class RegistrationPasswordViewModel : ViewModel() {

    private val model = RegistrationPasswordModel()
    val passwordKey = "PASSWORD_KEY"

    val password: MutableLiveData<String?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()

    fun setSavedData(data: Bundle?) {
        if (data != null) {
            if (data.containsKey(passwordKey)) {
                password.value = data.getString(passwordKey)
            }
        }
    }

    fun changeError(context: Context?, password: String) {
        when {
            password == "" -> {
                error.value = context?.getString(R.string.input_password_please)
            }
            !isPasswordValid(password) -> {
                error.value = context?.getString(R.string.password_is_not_correct)
            }
            else -> {
                error.value = null
            }
        }
    }


    private fun isPasswordValid(password: String): Boolean {
        //TODO(create rules for password)
        return true
    }
}