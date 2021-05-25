package com.example.organizeme.registrationBlock.password

class RegistrationPasswordModel {

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

    fun sendPassword(password: String): Boolean {
        //TODO(send it to server)
        return false;
    }
}