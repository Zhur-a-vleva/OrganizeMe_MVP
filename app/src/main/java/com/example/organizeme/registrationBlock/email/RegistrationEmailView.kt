package com.example.organizeme.registrationBlock.email

import android.os.Bundle
import android.text.Editable
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

class RegistrationEmailView : Fragment(), RegistrationEmailObserverInterface {

    private lateinit var viewModel: RegistrationEmailViewModel
    private lateinit var navigationController: NavController
    private lateinit var emailInputLayout: TextInputLayout

    companion object {
        const val name = "RegistrationEmailView"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = RegistrationEmailViewModel()
        navigationController = NavHostFragment.findNavController(this)
        return inflater.inflate(R.layout.registration_email_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailInputLayout = view.findViewById(R.id.registration_email_view_email_input_layout)
        val accountExist: TextView = view.findViewById(R.id.registration_email_account_exist)
        val nextButton: ImageView = view.findViewById(R.id.registration_email_view_next)

        viewModel.error.subscribe(::emailHasChanged)
        viewModel.email.subscribe(::setEmailHint)

        viewModel.setSavedData(arguments)

        emailInputLayout.editText?.addTextChangedListener {
            viewModel.emailHasChanged(context, it.toString())
        }

        accountExist.setOnClickListener {
            navigationController.navigate(R.id.signInFragment)
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

    override fun emailHasChanged(it: String?) {
        emailInputLayout.error = it
    }

    override fun setEmailHint(it: String?) {
        emailInputLayout.editText?.text = Editable.Factory.getInstance().newEditable(it)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.error.unsubscribe(::emailHasChanged)
        viewModel.email.unsubscribe(::setEmailHint)
    }

}

interface RegistrationEmailObserverInterface {
    fun emailHasChanged(it: String?)
    fun setEmailHint(it: String?)
}