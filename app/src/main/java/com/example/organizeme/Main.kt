package com.example.organizeme

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Main : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager.beginTransaction()
    private lateinit var sharedPreferences : SharedPreferences
    private val firstRunKey = "FIRST_RUN_KEY"
    //TODO: private val registrationFragment =
    //TODO: private val signInFragment =

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            setContentView(R.layout.registration_fragment)
        }
        else {
            setContentView(R.layout.fragment_container)
        }

        sharedPreferences = getSharedPreferences("com.example.organizeme", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean(firstRunKey, true)) {
            if (BuildConfig.DEBUG) {
                Toast.makeText(this, "First run", Toast.LENGTH_LONG).show()
            }
            //TODO: start "registration" fragment
            sharedPreferences.edit().putBoolean(firstRunKey, false).apply()
        }
        else {
            if (BuildConfig.DEBUG) {
                Toast.makeText(this, "Not first run", Toast.LENGTH_LONG).show()
            }
            //TODO: start "sign in" fragment
        }
    }
}
