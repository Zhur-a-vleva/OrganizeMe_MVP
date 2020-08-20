package com.example.organizeme.signInBlock

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.organizeme.ErrorDialogFragment
import com.example.organizeme.ErrorType
import com.example.organizeme.R
import com.google.android.material.textfield.TextInputLayout


class SignInFragment : Fragment(), SignInFragmentInterface {

    private lateinit var presenter: SignInPresenter
    private lateinit var navigationController: NavController

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
        return inflater.inflate(R.layout.sign_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailInput: EditText = view.findViewById(R.id.sign_in_email_input)
        val passwordInputLayout: TextInputLayout =
            view.findViewById(R.id.sign_in_password_input_layout)
        var isPasswordVisible = false
        val passwordInput: EditText = view.findViewById(R.id.sign_in_password_input)
        val forgotPassword: TextView = view.findViewById(R.id.forgot_password)
        val signInButton: Button = view.findViewById(R.id.sign_in)
        val signUp: TextView = view.findViewById(R.id.sign_up)

        emailInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(email: CharSequence?, start: Int, before: Int, count: Int) {
                if (!presenter.isValidEmail(email.toString())) {
                    //TODO: дизайн, написать, что почта неверно написана
                }
            }

        })

        passwordInputLayout.setEndIconOnClickListener {
            if (isPasswordVisible) {
                passwordInputLayout.setEndIconDrawable(R.drawable.eye)
                passwordInput.transformationMethod = PasswordTransformationMethod()
                isPasswordVisible = false
            } else {
                passwordInputLayout.setEndIconDrawable(R.drawable.crossed_out_eye)
                passwordInput.transformationMethod = null
                isPasswordVisible = true
            }
        }
        forgotPassword.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
            val dialog = alertDialog.create()

            val view = layoutInflater.inflate(R.layout.forgot_password_dialog, null)
            dialog.setView(view)

            val emailInputDialog: EditText = view.findViewById(R.id.email_input)

            emailInputDialog.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    email: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    if (!presenter.isValidEmail(email.toString())) {
                        //TODO: дизайн, написать, что почта неверно написана
                    }
                }
            })

            val sendPassword: Button = view.findViewById(R.id.send_password)

            sendPassword.setOnClickListener {
                if (presenter.isValidEmail(emailInputDialog.text.toString())) {
                    val email = emailInputDialog.text.toString()
                    presenter.onForgotPasswordClicked(email)
                    dialog.cancel()
                }
            }

            dialog.show()
        }

        signInButton.setOnClickListener {
            presenter.onSignInButtonClicked(
                emailInput.text.toString(),
                passwordInput.text.toString()
            )
        }

        signUp.setOnClickListener {
            navigationController.navigate(R.id.registrationEmailFragment)
        }

    }

    override fun signInFailed(error: ErrorType) {
        val bundle = Bundle()
        bundle.putString(ErrorDialogFragment.ERROR_TYPE_KEY, error.toString())
        val errorMessage = ErrorDialogFragment()
        errorMessage.arguments = bundle
        fragmentManager?.beginTransaction()?.let { errorMessage.show(it, errorMessage.tag) }
    }

    override fun showProfile(email: String) {
        navigationController.navigate(R.id.profileFragment)
    }

    override fun sendPassword(email: String, password: String) {
        //TODO: через бэк
    }
}

interface SignInFragmentInterface {
    fun signInFailed(error: ErrorType)
    fun showProfile(email: String)
    fun sendPassword(email: String, password: String)
}