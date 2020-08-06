package com.example.organizeme.signInBlock

import android.os.Bundle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SignInRepository {
    /*
    //temporary token
    private val token = "YFTSs_-BbAkmrn16cEuQ-7mT1TXDpTKEASL66lDUBXRzIljYA6HBSdMfjxFcPfGA"

    companion object {
        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.HEADERS)
            })
            .build()

        private val signInApi: SignInApi = Retrofit.Builder()
            .baseUrl("url")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SignInApi::class.java)
    }
     */

    fun checkEmail(email: String): Boolean {
        //TODO: чекнуть, существует ли такая почта
        //Test information
        if (email.equals("Zhur.a.vleva.Darya21@gmail.com")) {
            return true
        }
        return false
    }

    fun getPassword(email: String): String {
        //TODO: запросить пароль
        //Test information
        return "n5i9H3m7PW"
    }

}