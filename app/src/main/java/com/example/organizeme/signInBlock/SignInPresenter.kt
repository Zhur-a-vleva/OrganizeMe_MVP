package com.example.organizeme.signInBlock

import android.text.TextUtils
import android.util.Patterns
import com.example.organizeme.ErrorType


class SignInPresenter(private val view: SignInFragmentInterface) : SignInPresenterInterface {

    private val repository = SignInRepository()

    override fun onSignInButtonClicked(email: String, password: String) {
        //TODO: сделать предупреждение в поле, вместо диалога
        if (!isValidEmail(email)) {
            view.signInFailed(ErrorType.EMAIL_IS_NOT_CORRECT)
        }
        else if (!repository.checkEmail(email)) {
            view.signInFailed(ErrorType.EMAIL_IS_NOT_REGISTERED)
        }
        else if (repository.getPassword(email) != password) {
            view.signInFailed(ErrorType.PASSWORD_IS_NOT_CORRECT)
        } else {
            view.showProfile(email)
        }
    }

    override fun onForgotPasswordClicked(email: String) {
        view.sendPassword(email, repository.getPassword(email))
    }

    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

    fun isValidPassword(password: String): Boolean {
        if (password.length >= 8) {
            return true
        }
        return false
    }

}

interface SignInPresenterInterface {
    fun onSignInButtonClicked(email: String, password: String)
    fun onForgotPasswordClicked(email: String)
}