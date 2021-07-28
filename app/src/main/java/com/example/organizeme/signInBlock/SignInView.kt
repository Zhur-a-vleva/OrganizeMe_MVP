package com.example.organizeme.signInBlock

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.organizeme.R
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.sign_in_view.*


class SignInView : Fragment(R.layout.sign_in_view) {

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var navigationController: NavController
    private var errorState = false

    companion object {
        const val name = "SignInView"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.theme?.applyStyle(R.style.ButtonStyle, true)
        navigationController = NavHostFragment.findNavController(this)

        viewModel.signInError.observe(viewLifecycleOwner, Observer { signInError ->
            if (signInError == null) {
                errorState = false
            } else {
                errorState = true
                val dialog = AlertDialog.Builder(context).create()
                val dialogView = layoutInflater.inflate(R.layout.sign_in_error, null)
                dialog.setView(dialogView)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCanceledOnTouchOutside(false)
                dialog.setCancelable(false)

                val errorText: TextView = dialogView.findViewById(R.id.error)
                val tryAgain: TextView = dialogView.findViewById(R.id.tryAgain)

                errorText.text = signInError

                tryAgain.setOnClickListener {
                    dialog.cancel()
                }

                dialog.show()
            }
        })

        forgotPassword.setOnClickListener {
            val dialog = AlertDialog.Builder(context).create()

            val dialogView = layoutInflater.inflate(R.layout.forgot_password, null)
            dialog.setView(dialogView)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(false)

            val emailInputDialogLayout: TextInputLayout =
                dialogView.findViewById(R.id.emailInputDialogLayout)
            val sendPassword: Button = dialogView.findViewById(R.id.sendPassword)


            sendPassword.setOnClickListener {
                if (emailInputDialogLayout.editText?.text.toString() != "") {
                    viewModel.onForgotPasswordClicked(emailInputDialogLayout.editText?.text.toString())
                }
                dialog.cancel()
            }

            dialog.show()
        }

        signInButton.setOnClickListener {
            val imm: InputMethodManager =
                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(
                signInButton.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )

            viewModel.changeSignInError(
                context,
                emailInputLayout.editText?.text.toString(),
                passwordInputLayout.editText?.text.toString()
            )
            if (!errorState) {
                navigationController.navigate(R.id.profileView)
            }
        }

        signUp.setOnClickListener {
            navigationController.navigate(R.id.registrationEmailView)
        }

    }

}