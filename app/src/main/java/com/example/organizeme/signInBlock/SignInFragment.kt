package com.example.organizeme.signInBlock

import android.app.AlertDialog
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.organizeme.ErrorType
import com.example.organizeme.R
import com.google.android.material.textfield.TextInputLayout


class SignInFragment : Fragment(), SignInFragmentInterface {

    private lateinit var presenter: SignInPresenter
    private lateinit var navigationController: NavController

    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout

    companion object {
        const val name = "SignInFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = SignInPresenter(this)
        navigationController = NavHostFragment.findNavController(this)
        context?.theme?.applyStyle(R.style.buttonStyle, true)
        return inflater.inflate(R.layout.sign_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailInputLayout = view.findViewById(R.id.sign_in_email_input_layout)

        passwordInputLayout =
            view.findViewById(R.id.sign_in_password_input_layout)
        var isPasswordVisible = false

        val forgotPassword: TextView = view.findViewById(R.id.sign_in_forgot_password)

        val signInButton: Button = view.findViewById(R.id.sign_in_button)

        val signUp: TextView = view.findViewById(R.id.sign_in_sign_up)

        emailInputLayout.editText?.addTextChangedListener {
            if (!presenter.isValidEmail(it.toString())) {
                emailInputLayout.error = getString(R.string.email_is_not_correct)
            }
            else {
                emailInputLayout.error = null
            }
        }

        passwordInputLayout.setEndIconOnClickListener {
            if (isPasswordVisible) {
                passwordInputLayout.setEndIconDrawable(R.drawable.eye)
                passwordInputLayout.editText?.transformationMethod = PasswordTransformationMethod()
                isPasswordVisible = false
            } else {
                passwordInputLayout.setEndIconDrawable(R.drawable.crossed_out_eye)
                passwordInputLayout.editText?.transformationMethod = null
                isPasswordVisible = true
            }
        }

        forgotPassword.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
            val dialog = alertDialog.create()

            val view = layoutInflater.inflate(R.layout.forgot_password, null)
            dialog.setView(view)

            val emailInputDialogLayout: TextInputLayout =
                view.findViewById(R.id.forgot_password_email_input_layout)

            emailInputDialogLayout.editText?.addTextChangedListener {
                if (!presenter.isValidEmail(it.toString())) {
                    emailInputDialogLayout.error = getString(R.string.email_is_not_correct)
                } else {
                    emailInputDialogLayout.error = null
                }
            }

            val sendPassword: Button = view.findViewById(R.id.forgot_password_send_password)

            sendPassword.setOnClickListener {
                if (presenter.isValidEmail(emailInputDialogLayout.editText?.text.toString())) {
                    val email = emailInputDialogLayout.editText?.text.toString()
                    presenter.onForgotPasswordClicked(email)
                    dialog.cancel()
                }
            }

            dialog.show()
        }

        signInButton.setOnClickListener {
            presenter.onSignInButtonClicked(
                emailInputLayout.editText?.text.toString(),
                passwordInputLayout.editText?.text.toString()
            )
        }

        signUp.setOnClickListener {
            navigationController.navigate(R.id.registrationEmailView)
        }

    }

    override fun signInFailed(error: ErrorType) {
        if (error == ErrorType.EMAIL_IS_NOT_CORRECT) {
            emailInputLayout.error = getString(R.string.email_is_not_correct)
        } else if (error == ErrorType.EMAIL_IS_NOT_REGISTERED) {
            emailInputLayout.error = getString(R.string.email_is_not_registered)
        } else if (error == ErrorType.PASSWORD_IS_NOT_CORRECT) {
            passwordInputLayout.error = getString(R.string.password_is_not_correct)
        }
    }

    override fun showProfile(email: String) {
        navigationController.navigate(R.id.profileFragment)
    }

}

interface SignInFragmentInterface {
    fun signInFailed(error: ErrorType)
    fun showProfile(email: String)
}