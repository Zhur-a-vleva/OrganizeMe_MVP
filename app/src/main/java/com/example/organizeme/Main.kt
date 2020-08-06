package com.example.organizeme

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.organizeme.registrationBlock.RegistrationEmailFragment
import com.example.organizeme.signInBlock.SignInFragment

class Main : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager.beginTransaction()
    private lateinit var sharedPreferences: SharedPreferences
    private val firstRunKey = "FIRST_RUN_KEY"
    private val registrationActiveKey = "REGISTRATION_ACTIVE_KEY"
    private val registrationFragment =
        RegistrationEmailFragment.newInstance(this)
    private val signInFragment = SignInFragment.newInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)

        //initialize sharedPreferences
        sharedPreferences = getSharedPreferences("com.example.organizeme", Context.MODE_PRIVATE)

        //checking first run
        if (sharedPreferences.getBoolean(firstRunKey, true)) {
            sharedPreferences.edit().putBoolean(registrationActiveKey, true).apply()

            //make toast for debug version
            if (BuildConfig.DEBUG) {
                Toast.makeText(this, "First run", Toast.LENGTH_LONG).show()
            }

            //show the "registration" fragment, if it's the first run
            fragmentManager
                .add(R.id.fragment_container, registrationFragment)
                .commit()
            fragmentManager.addToBackStack(RegistrationEmailFragment.name)

            //edit "firstRun" state
            sharedPreferences.edit().putBoolean(firstRunKey, false).apply()
        } else {
            sharedPreferences.edit().putBoolean(registrationActiveKey, false).apply()

            //make toast for debug version
            if (BuildConfig.DEBUG) {
                Toast.makeText(this, "Not first run", Toast.LENGTH_LONG).show()
            }

            //show the "sign in" fragment, if it's not the first run
            fragmentManager
                .add(R.id.fragment_container, signInFragment)
                .commit()
            fragmentManager.addToBackStack(SignInFragment.name)
        }
    }

    override fun onBackPressed() {
        if (sharedPreferences.getBoolean(registrationActiveKey, true)) {
            fragmentManager.remove(registrationFragment)
        } else {
            fragmentManager.remove(signInFragment)
        }
        super.onBackPressed()
    }
}
