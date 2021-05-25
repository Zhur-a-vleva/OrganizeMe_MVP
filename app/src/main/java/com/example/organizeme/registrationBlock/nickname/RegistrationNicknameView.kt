package com.example.organizeme.registrationBlock.nickname

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
import kotlinx.android.synthetic.main.registration_nickname_view.*

class RegistrationNicknameView : Fragment(R.layout.registration_nickname_view) {

    private val viewModel: RegistrationNicknameViewModel by viewModels()
    private lateinit var navigationController: NavController

    companion object {
        const val name = "RegistrationNicknameView"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navigationController = NavHostFragment.findNavController(this)

        var data = arguments
        if (data == null) {
            data = Bundle()
        }

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            nicknameInputLayout.error = error

        })
        viewModel.nickname.observe(viewLifecycleOwner, Observer { nickname ->
            nicknameInputLayout.editText?.text =
                Editable.Factory.getInstance().newEditable(nickname)
        })

        viewModel.setSavedData(arguments)

        nicknameInputLayout.editText?.addTextChangedListener { nickname ->
            viewModel.changeError(context, nickname.toString())
        }

        prevButton.setOnClickListener {
            data.putString(viewModel.nicknameKey, nicknameInputLayout.editText?.text.toString())
            navigationController.navigate(R.id.registrationEmailView, data)
        }

        nextButton.setOnClickListener {
            data.putString(viewModel.nicknameKey, nicknameInputLayout.editText?.text.toString())
            navigationController.navigate(R.id.registrationPasswordView, data)
        }
    }
}