package com.example.organizeme.signInBlock

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SignInApi {

    //TODO: complete getEmail and getPassword

    @GET("way")

    fun checkEmail(@Path("way") path: String, @Query("access_token") token: String) : Single<Any>

    @GET("way")

    fun getPassword(@Path("way") path: String, @Query("access_token") token: String) : Single<Any>
}