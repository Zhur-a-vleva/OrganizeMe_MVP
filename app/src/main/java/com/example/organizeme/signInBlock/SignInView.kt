package com.example.organizeme.signInBlock

import android.app.AlertDialog
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.organizeme.R
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.sign_in_view.*


class SignInView : Fragment(R.layout.sign_in_view) {

    //TODO: fix email checking (Button pressed -> email checking, error displaying)

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var navigationController: NavController

    companion object {
        const val name = "SignInView"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.theme?.applyStyle(R.style.ButtonStyle, true)
        navigationController = NavHostFragment.findNavController(this)

        var sendEndIconState = true

        viewModel.emailError.observe(viewLifecycleOwner, Observer { emailError ->
            emailInputLayout.error = emailError
        })
        viewModel.endIconIsActive.observe(viewLifecycleOwner, Observer { state ->
            if (state) {
                passwordInputLayout.setEndIconDrawable(R.drawable.eye)
                passwordInputLayout.editText?.transformationMethod = PasswordTransformationMethod()
                sendEndIconState = false
            } else {
                passwordInputLayout.setEndIconDrawable(R.drawable.crossed_out_eye)
                passwordInputLayout.editText?.transformationMethod = null
                sendEndIconState = true
            }
        })

        emailInputLayout.editText?.addTextChangedListener { email ->
            viewModel.changeEmailError(context, email.toString())
        }

        passwordInputLayout.setEndIconOnClickListener {
            viewModel.changeEndIconState(sendEndIconState)
        }

        forgotPassword.setOnClickListener {
            val dialog = AlertDialog.Builder(context).create()

            val dialogView = layoutInflater.inflate(R.layout.forgot_password, null)
            dialog.setView(dialogView)

            val emailInputDialogLayout: TextInputLayout =
                dialogView.findViewById(R.id.emailInputDialogLayout)
            val sendPassword: Button = dialogView.findViewById(R.id.sendPassword)

            viewModel.dialogEmailError.observe(viewLifecycleOwner, Observer { error ->
                emailInputDialogLayout.error = error
            })

            emailInputDialogLayout.editText?.addTextChangedListener { email ->
                viewModel.changeDialogEmailError(context, email.toString())
            }

            sendPassword.setOnClickListener {
                if (emailInputDialogLayout.error == null) {
                    viewModel.onForgotPasswordClicked(emailInputDialogLayout.editText?.text.toString())
                    dialog.cancel()
                }
            }

            dialog.show()
        }

        signInButton.setOnClickListener {
            if (emailInputLayout.error == null && passwordInputLayout.error == null) {
                navigationController.navigate(R.id.profileView)
            }
        }

        signUp.setOnClickListener {
            navigationController.navigate(R.id.registrationEmailView)
        }

    }

}