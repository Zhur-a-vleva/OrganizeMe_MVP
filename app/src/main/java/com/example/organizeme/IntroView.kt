package com.example.organizeme

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.organizeme.R
import kotlinx.android.synthetic.main.intro.*

class IntroView : Fragment(R.layout.intro) {

    private lateinit var navigationController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navigationController = NavHostFragment.findNavController(this)

        nextButton.setOnClickListener {
            navigationController.navigate(R.id.calendarView)
        }

    }
}