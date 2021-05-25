package com.example.organizeme.registrationBlock.password

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
import kotlinx.android.synthetic.main.registration_password_view.*

class RegistrationPasswordView : Fragment(R.layout.registration_password_view) {

    private val viewModel: RegistrationPasswordViewModel by viewModels()
    private lateinit var navigationController: NavController

    companion object {
        const val name = "RegistrationPasswordView"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navigationController = NavHostFragment.findNavController(this)

        var data = arguments
        if (data == null) {
            data = Bundle()
        }

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            passwordInput.error = error

        })
        viewModel.password.observe(viewLifecycleOwner, Observer { password ->
            passwordInputLayout.editText?.text =
                Editable.Factory.getInstance().newEditable(password)
        })

        viewModel.setSavedData(arguments)

        passwordInputLayout.editText?.addTextChangedListener { password ->
            viewModel.changeError(context, password.toString())
        }

        prevButton.setOnClickListener {
            data.putString(viewModel.passwordKey, passwordInputLayout.editText?.text.toString())
            navigationController.navigate(R.id.registrationNicknameView, data)
        }

        nextButton.setOnClickListener {
            //TODO(navigate to last stage)
        }
    }
}