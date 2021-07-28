package com.example.organizeme.signInBlock

class SignInModel {
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

    fun checkEmailExistence(email: String): Boolean {
        //TODO(чекнуть, существует ли такая почта)
        //Test information
        if (email.toLowerCase() == "zhur.a.vleva.darya21@gmail.com") {
            return true
        }
        return false
    }

    fun getPassword(email: String): String {
        //TODO(запросить пароль с БД)
        //Test information
        return "."
    }

    fun sendPassword(email: String) {
        //TODO(Send password through back)
    }

}