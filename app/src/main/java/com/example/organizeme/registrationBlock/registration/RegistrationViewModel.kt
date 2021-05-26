package com.example.organizeme.registrationBlock.registration

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {

    private val model = RegistrationModel()
    private val emailKey = "EMAIL_KEY"
    private val nicknameKey = "NICKNAME_KEY"
    private val passwordKey = "PASSWORD_KEY"

    val email: MutableLiveData<String?> = MutableLiveData()
    val nickname: MutableLiveData<String?> = MutableLiveData()
    val password: MutableLiveData<String?> = MutableLiveData()

    fun setSavedData(data: Bundle?) {
        if (data != null) {
            if (data.containsKey(emailKey)) {
                email.value = data.getString(emailKey)
            }
            if (data.containsKey(nicknameKey)) {
                nickname.value = data.getString(nicknameKey)
            }
            if (data.containsKey(passwordKey)) {
                password.value = data.getString(passwordKey)
            }
        }
    }

}