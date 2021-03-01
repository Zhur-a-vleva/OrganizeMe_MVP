package com.example.organizeme.registrationBlock.nickname

import androidx.fragment.app.Fragment

class RegistrationNicknamePresenter(private val view: RegistrationNicknameFragment) : Fragment(),
    RegistrationNicknamePresenterInterface {

    private val repository = RegistrationNicknameRepository()

    fun isNicknameExist(nickname: String): Boolean {
        //TODO
        return true
    }

    fun isNicknameValid(nickname: String): Boolean {
        //TODO
        return true
    }
}

interface RegistrationNicknamePresenterInterface {
    //TODO(write or delete)
}