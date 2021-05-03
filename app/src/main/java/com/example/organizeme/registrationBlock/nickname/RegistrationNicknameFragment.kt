package com.example.organizeme.registrationBlock.nickname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.organizeme.R
import com.google.android.material.textfield.TextInputLayout

class RegistrationNicknameFragment : Fragment(), RegistrationNicknameInterface {

    private lateinit var presenter: RegistrationNicknamePresenter
    private lateinit var navigationController: NavController

    companion object {
        const val name = "RegistrationNicknameFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = RegistrationNicknamePresenter(this)
        navigationController = NavHostFragment.findNavController(this)
        return inflater.inflate(R.layout.registration_nickname_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO(use? bundle)

        var bundle: Bundle? = arguments

        var nicknameInputLayout: TextInputLayout =
            view.findViewById(R.id.registration_nickname_fragment_nickname_input_layout)

        val prevButton: ImageView = view.findViewById(R.id.registration_nickname_fragment_prev)
        val nextButton: ImageView = view.findViewById(R.id.registration_nickname_fragment_next)

        nicknameInputLayout.editText?.addTextChangedListener {
            if (it.toString() == "") {
                //TODO(check is it work)
                nicknameInputLayout.error = null;
            } else if (presenter.isNicknameExist(it.toString())) {
                nicknameInputLayout.error = getString(R.string.nickname_is_exist)
            } else if (!presenter.isNicknameValid(it.toString())) {
                //TODO(add types of errors)
                nicknameInputLayout.error = getString(R.string.nickname_is_not_correct)
            } else {
                nicknameInputLayout.error = null;
            }
        }

        prevButton.setOnClickListener {
            navigationController.navigate(R.id.registrationEmailView, bundle)
        }

        nextButton.setOnClickListener {
            //TODO(navigationController.navigate(R.id.registrationPasswordFragment))
        }
    }
}

interface RegistrationNicknameInterface {
    //TODO(write or delete)
}