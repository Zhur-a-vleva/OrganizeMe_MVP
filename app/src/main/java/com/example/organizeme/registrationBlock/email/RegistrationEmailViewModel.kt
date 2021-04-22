package com.example.organizeme.registrationBlock.email

import android.text.TextUtils
import android.util.Patterns
import com.example.organizeme.Observable

class RegistrationEmailViewModel {

    private val model = RegistrationEmailModel()

    val email: Observable<String?> = Observable(null)

    init {
        load()
    }

    private fun load()

    fun isEmailExist(email: String): Boolean {
        return model.checkEmail(email)
    }

    fun isEmailValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

}
