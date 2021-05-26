package com.example.organizeme.registrationBlock.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.organizeme.R
import kotlinx.android.synthetic.main.registration.*
import kotlinx.android.synthetic.main.registration_nickname_view.prevButton

class RegistrationView : Fragment(R.layout.registration) {

    //TODO(stop to naviagte when email or nick is empty)

    private val viewModel: RegistrationViewModel by viewModels()
    private lateinit var navigationController: NavController

    companion object {
        const val name = "RegistrationView"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navigationController = NavHostFragment.findNavController(this)

        var data = arguments
        if (data == null) {
            data = Bundle()
        }

        viewModel.email.observe(viewLifecycleOwner, Observer { email ->
            checkEmail.text = email
        })
        viewModel.nickname.observe(viewLifecycleOwner, Observer { nickname ->
            checkNickname.text = nickname
        })
        viewModel.password.observe(viewLifecycleOwner, Observer { password ->
            checkPassword.text = password
        })

        viewModel.setSavedData(arguments)

        prevButton.setOnClickListener {
            navigationController.navigate(R.id.registrationEmailView, data)
        }

        signUpButton.setOnClickListener {
            //TODO(send information to server)
            navigationController.navigate(R.id.profileView, data)
        }
    }
}