package com.example.organizeme.registrationBlock.email

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.organizeme.R
import com.google.android.material.textfield.TextInputLayout

class RegistrationEmailFragment : Fragment(), RegistrationEmailFragmentInterface {

    private lateinit var presenter: RegistrationEmailPresenter
    private lateinit var navigationController: NavController

    companion object {
        const val name = "RegistrationEmailFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = RegistrationEmailPresenter(this)
        navigationController = NavHostFragment.findNavController(this)
        return inflater.inflate(R.layout.registration_email_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO(use? bundle)

        var emailInputLayout: TextInputLayout = view.findViewById(R.id.registration_email_fragment_email_input_layout)

        val accountExist: TextView = view.findViewById(R.id.registration_email_account_exist)

        val nextButton: ImageView = view.findViewById(R.id.registration_email_fragment_next)

        emailInputLayout.editText?.addTextChangedListener {
            if (it.toString() == "") {
                //TODO(check is it work)
                emailInputLayout.error = null;
            }
            if (presenter.isEmailExist(it.toString())) {
                emailInputLayout.error = getString(R.string.email_is_exist)
            }
            else if (!presenter.isEmailValid(it.toString())) {
                emailInputLayout.error = getString(R.string.email_is_not_correct)
            }
            else {
                emailInputLayout.error = null
            }
        }

        accountExist.setOnClickListener {
            navigationController.navigate(R.id.signInFragment)
        }

        nextButton.setOnClickListener {
            if (emailInputLayout.error == null) {
                navigationController.navigate(R.id.registrationNicknameFragment)
            }
        }
    }

}

interface RegistrationEmailFragmentInterface {

}