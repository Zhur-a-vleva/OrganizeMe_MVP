package com.example.organizeme.registrationBlock.email

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.organizeme.R
import kotlinx.android.synthetic.main.registration_email_view.*

class RegistrationEmailView : Fragment(R.layout.registration_email_view) {

    private val viewModel: RegistrationEmailViewModel by viewModels()
    private lateinit var navigationController: NavController

    companion object {
        const val name = "RegistrationEmailView"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navigationController = NavHostFragment.findNavController(this)

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            emailInputLayout.error = error

        })
        viewModel.email.observe(viewLifecycleOwner, Observer { email ->
            emailInputLayout.editText?.text = Editable.Factory.getInstance().newEditable(email)
        })

        viewModel.setSavedData(arguments)

        emailInputLayout.editText?.addTextChangedListener { email ->
            viewModel.changeError(context, email.toString())
        }

        accountExist.setOnClickListener {
            navigationController.navigate(R.id.signInView)
        }

        nextButton.setOnClickListener {
            if (emailInputLayout.error == null) {
                var data = arguments
                if (data == null) {
                    data = Bundle()
                }
                data.putString(viewModel.emailKey, emailInputLayout.editText?.text.toString())
                navigationController.navigate(R.id.registrationNicknameView, data)
            }
        }
    }
}