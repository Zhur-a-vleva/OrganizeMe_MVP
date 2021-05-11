package com.example.organizeme.registrationBlock

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RegistrationApi {

    //TODO(check email)

    @GET("way")

    fun checkEmail(@Path("way") path: String, @Query("access_token") token: String) : Single<Any>

    @GET("way")

    fun checkNickname(@Path("way") path: String, @Query("access_token") token: String) : Single<Any>
}