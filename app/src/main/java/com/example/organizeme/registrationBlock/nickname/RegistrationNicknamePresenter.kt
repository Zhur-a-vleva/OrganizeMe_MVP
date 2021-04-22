package com.example.organizeme.registrationBlock.nickname

import androidx.fragment.app.Fragment

class RegistrationNicknamePresenter(private val view: RegistrationNicknameFragment) : Fragment() {

    private val repository = RegistrationNicknameRepository()

    fun isNicknameExist(nickname: String): Boolean {
        return repository.checkNickname(nickname)
    }

    fun isNicknameValid(nickname: String): Boolean {
        //TODO(create rules for nickname)
        return true
    }
}