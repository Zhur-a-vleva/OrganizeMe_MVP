package com.example.organizeme.registrationBlock.email

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organizeme.R

class RegistrationEmailViewModel : ViewModel() {

    private val model = RegistrationEmailModel()
    val emailKey = "EMAIL_KEY"

    val email: MutableLiveData<String?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()

    fun setSavedData(data: Bundle?) {
        if (data != null) {
            if (data.containsKey(emailKey)) {
                email.value = data.getString(emailKey)
            }
        }
    }

    fun changeError(context: Context?, email: String) {
        when {
            email == "" -> {
                error.value = context?.getString(R.string.input_email_please)
            }
            isEmailExist(email) -> {
                error.value = context?.getString(R.string.email_is_exist)
            }
            !isEmailValid(email) -> {
                error.value = context?.getString(R.string.email_or_password_is_not_correct)
            }
            else -> {
                error.value = null
            }
        }
    }

    private fun isEmailExist(email: String): Boolean {
        return model.checkEmail(email)
    }

    private fun isEmailValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

}
