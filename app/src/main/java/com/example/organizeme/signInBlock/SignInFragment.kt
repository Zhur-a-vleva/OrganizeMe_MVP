package com.example.organizeme.signInBlock

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.organizeme.ErrorDialogFragment
import com.example.organizeme.ErrorType
import com.example.organizeme.R
import com.example.organizeme.profileBlock.ProfileFragment
import com.example.organizeme.registrationBlock.RegistrationEmailFragment


class SignInFragment : Fragment(R.layout.sign_in_fragment), SignInFragmentInterface {

    private val presenter = SignInPresenter(this)
    private val fragmentTransaction = fragmentManager?.beginTransaction()

    companion object {

        const val name = "SignInFragment"
        private lateinit var context: Context

        fun newInstance(cont: Context): SignInFragment {
            context = cont
            return SignInFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailInput: EditText = view.findViewById(R.id.email_input)
        val passwordInput: EditText = view.findViewById(R.id.password_input)
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
            fragmentTransaction?.replace(
                R.id.fragment_container,
                RegistrationEmailFragment.newInstance(SignInFragment.context)
            )
                ?.commit()
            fragmentTransaction?.addToBackStack(RegistrationEmailFragment.name)
        }
    }

    override fun signInFailed(error: ErrorType) {
        val bundle = Bundle()
        bundle.putString(ErrorDialogFragment.ERROR_TYPE_KEY, error.toString())
        val errorMessage = ErrorDialogFragment()
        errorMessage.arguments = bundle
        fragmentTransaction?.let { errorMessage.show(it, errorMessage.tag) }
    }

    override fun showProfile(email: String) {
        fragmentTransaction?.replace(
            R.id.fragment_container,
            ProfileFragment.newInstance(Companion.context, email)
        )
            ?.commit()
        fragmentTransaction?.addToBackStack(ProfileFragment.name)
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