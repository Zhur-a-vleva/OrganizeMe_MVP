package com.example.organizeme.registrationBlock.email

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.example.organizeme.Observable
import com.example.organizeme.R

class RegistrationEmailViewModel(savedData: Bundle?) {

    private val model = RegistrationEmailModel()
    val emailKey = "EMAIL_KEY"

    val error: Observable<String?> = Observable(null)

    init {
        setSavedData(savedData)
    }

    private fun setSavedData(data: Bundle?) {
        if (data != null) {
            if (data.containsKey(emailKey)) {
                error.value = data.getString(emailKey)
            }
        }
    }

    fun emailHasChanged(context: Context?, email: String) {
        if (email == "") {
            error.value = context?.getString(R.string.input_email_please)
        }
        if (isEmailExist(email)) {
            error.value = context?.getString(R.string.email_is_exist)
        } else if (!isEmailValid(email)) {
            error.value = context?.getString(R.string.email_is_not_correct)
        } else {
            error.value = null
        }
    }

    fun isEmailExist(email: String): Boolean {
        return model.checkEmail(email)
    }

    fun isEmailValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

}
