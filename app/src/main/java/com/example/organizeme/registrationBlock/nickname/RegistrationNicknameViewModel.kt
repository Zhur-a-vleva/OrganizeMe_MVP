package com.example.organizeme.registrationBlock.nickname

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organizeme.R

class RegistrationNicknameViewModel : ViewModel() {

    private val model = RegistrationNicknameModel()
    val nicknameKey = "NICKNAME_KEY"

    val nickname: MutableLiveData<String?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()

    fun setSavedData(data: Bundle?) {
        if (data != null) {
            if (data.containsKey(nicknameKey)) {
                nickname.value = data.getString(nicknameKey)
            }
        }
    }

    fun changeError(context: Context?, nickname: String) {
        when {
            nickname == "" -> {
                error.value = context?.getString(R.string.input_nickname_please)
            }
            isNicknameExist(nickname) -> {
                error.value = context?.getString(R.string.nickname_is_exist)
            }
            !isNicknameValid(nickname) -> {
                error.value = context?.getString(R.string.nickname_is_not_correct)
            }
            else -> {
                error.value = null
            }
        }
    }

    private fun isNicknameExist(nickname: String): Boolean {
        return model.checkNickname(nickname)
    }

    private fun isNicknameValid(nickname: String): Boolean {
        //TODO(create rules for nickname)
        return true
    }
}