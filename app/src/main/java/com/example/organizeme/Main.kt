package com.example.organizeme

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

class Main : AppCompatActivity() {

    //TODO(Make toast more beautiful)

    private lateinit var sharedPreferences: SharedPreferences
    private val firstRunKey = "FIRST_RUN_KEY"
    private val registrationActiveKey = "REGISTRATION_ACTIVE_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)

        //initialize sharedPreferences
        sharedPreferences = getSharedPreferences("com.example.organizeme", Context.MODE_PRIVATE)

        val navigationController =
            supportFragmentManager.findFragmentById(R.id.navigation_fragment)?.findNavController()

        //checking first run
        if (sharedPreferences.getBoolean(firstRunKey, true)) {
            sharedPreferences.edit().putBoolean(registrationActiveKey, true).apply()

            //make toast for debug version
            if (BuildConfig.DEBUG) {
                Toast.makeText(this, "First run", Toast.LENGTH_LONG).show()
            }

            //show the "registration" fragment, if it's the first run
            navigationController?.navigate(R.id.registrationEmailFragment)

            //edit "firstRun" state
            sharedPreferences.edit().putBoolean(firstRunKey, false).apply()
        } else {
            sharedPreferences.edit().putBoolean(registrationActiveKey, false).apply()

            //make toast for debug version
            if (BuildConfig.DEBUG) {
                Toast.makeText(this, "Not first run", Toast.LENGTH_LONG).show()
            }

            //show the "sign in" fragment, if it's not the first ru
            navigationController?.navigate(R.id.signInFragment)
        }
    }

}
