package com.example.organizeme

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

class Main : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)

        val sharedPreferences = getSharedPreferences("com.example.organizeme", Context.MODE_PRIVATE)
        val navigationController =
            supportFragmentManager.findFragmentById(R.id.navigation_fragment)?.findNavController()

        viewModel.firstRun.observe(this, Observer { isFirstRun ->
            if (isFirstRun) {
                navigationController?.navigate(R.id.registrationEmailView)
            } else {
                navigationController?.navigate(R.id.signInView)
            }
        })
        viewModel.navigate(sharedPreferences)
    }

}
