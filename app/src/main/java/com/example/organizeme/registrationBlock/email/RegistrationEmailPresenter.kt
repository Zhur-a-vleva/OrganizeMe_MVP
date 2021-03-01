package com.example.organizeme.registrationBlock.email

import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment

class RegistrationEmailPresenter(private val view: RegistrationEmailFragmentInterface) :
    Fragment() {

    private val repository = RegistrationEmailRepository()

    fun isEmailExist(email: String): Boolean {
        return repository.checkEmail(email)
    }

    fun isEmailValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

}
