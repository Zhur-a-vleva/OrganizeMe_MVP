package com.example.organizeme.registrationBlock.email

class RegistrationEmailRepository {

    /*
    //temporary token
    private val token = "YFTSs_-BbAkmrn16cEuQ-7mT1TXDpTKEASL66lDUBXRzIljYA6HBSdMfjxFcPfGA"

    companion object {
        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.HEADERS)
            })
            .build()

        private val registrationEmailApi: = Retrofit.Builder()
            .baseUrl("url")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RegistrationEmailApi::class.java)
    }
     */

    fun checkEmail(email: String): Boolean {
        //TODO: чекнуть, существует ли такая почта
        //Test information
        if (email.toLowerCase() == "zhur.a.vleva.darya21@gmail.com") {
            return true
        }
        return false
    }
}