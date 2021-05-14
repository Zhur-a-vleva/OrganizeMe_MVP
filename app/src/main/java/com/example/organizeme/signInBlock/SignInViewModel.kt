package com.example.organizeme.signInBlock

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organizeme.R


class SignInViewModel() : ViewModel() {

    private val repository = SignInModel()

    val endIconIsActive: MutableLiveData<Boolean> = MutableLiveData()
    val emailError: MutableLiveData<String?> = MutableLiveData()
    val dialogEmailError: MutableLiveData<String?> = MutableLiveData()

    fun changeEndIconState(state: Boolean) {
        endIconIsActive.value = state
    }

    fun changeEmailError(context: Context?, email: String) {
        when {
            email == "" -> {
                emailError.value = context?.getString(R.string.input_email_please)
            }
            !isEmailValid(email) -> {
                emailError.value = context?.getString(R.string.email_is_not_correct)
            }
            else -> {
                emailError.value = null
            }
        }
    }

    fun changeDialogEmailError(context: Context?, email: String) {
        when {
            email == "" -> {
                dialogEmailError.value = context?.getString(R.string.input_email_please)
            }
            !isEmailValid(email) -> {
                dialogEmailError.value = context?.getString(R.string.email_is_not_correct)
            }
            else -> {
                dialogEmailError.value = null
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

}
