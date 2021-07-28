package com.example.organizeme.signInBlock

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organizeme.R


class SignInViewModel : ViewModel() {

    private val repository = SignInModel()

    val signInError: MutableLiveData<String?> = MutableLiveData()

    fun changeSignInError(context: Context?, email: String, password: String) {
        when {
            email == "" -> {
                signInError.value = context?.getString(R.string.input_email_please)
            }
            password == "" -> {
                signInError.value = context?.getString(R.string.input_password_please)
            }
            !isEmailValid(email) || !isPasswordRight(email, password) -> {
                signInError.value = context?.getString(R.string.email_or_password_is_not_correct)
            }
            !isEmailExist(email) -> {
                signInError.value = context?.getString(R.string.email_is_not_exist)
            }
            else -> {
                signInError.value = null
            }
        }
    }

    fun onForgotPasswordClicked(email: String) {
        repository.sendPassword(email)
    }

    private fun isEmailValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

    private fun isEmailExist(email: String): Boolean {
        return repository.checkEmailExistence(email)
    }

    private fun isPasswordRight(email: String, password: String): Boolean {
        if (repository.getPassword(email) == password) {
            return true
        }
        return false
    }
}
