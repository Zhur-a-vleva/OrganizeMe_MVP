package com.example.organizeme.registrationBlock.nickname

class RegistrationNicknameRepository {

    /*
    //temporary token
    private val token = "YFTSs_-BbAkmrn16cEuQ-7mT1TXDpTKEASL66lDUBXRzIljYA6HBSdMfjxFcPfGA"

    companion object {
        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.HEADERS)
            })
            .build()

        private val registrationApi: = Retrofit.Builder()
            .baseUrl("url")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RegistrationApi::class.java)
    }
     */

    fun checkNickname(nickname: String): Boolean {
        //TODO(check is this nickname exist)
        //testInformation
        if (nickname.toLowerCase() == "zhur.a.vlvea") {
            return true;
        }
        return false;
    }
}