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

class RegistrationNicknameView : Fragment(), RegistrationNicknameInterface {

    private lateinit var viewModel: RegistrationNicknameViewModel
    private lateinit var navigationController: NavController
    private lateinit var nicknameInputLayout: TextInputLayout

    companion object {
        const val name = "RegistrationNicknameView"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = RegistrationNicknameViewModel(arguments)
        navigationController = NavHostFragment.findNavController(this)
        return inflater.inflate(R.layout.registration_nickname_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nicknameInputLayout =
            view.findViewById(R.id.registration_nickname_fragment_nickname_input_layout)
        val prevButton: ImageView = view.findViewById(R.id.registration_nickname_fragment_prev)
        val nextButton: ImageView = view.findViewById(R.id.registration_nickname_fragment_next)
        var data = arguments
        if (data == null) {
            data = Bundle()
        }

        viewModel.error.subscribe(::nicknameHasChanged)
        viewModel.nickname.subscribe(::setNicknameHint)

        viewModel.setSavedData(arguments)

        nicknameInputLayout.editText?.addTextChangedListener {
            viewModel.nicknameHasChanged(context, it.toString())
        }

        prevButton.setOnClickListener {
            data.putString(viewModel.nicknameKey, nicknameInputLayout.editText?.text.toString())
            navigationController.navigate(R.id.registrationEmailView, data)
        }

        nextButton.setOnClickListener {
            //TODO(navigationController.navigate(R.id.registrationPasswordFragment))
        }
    }

    override fun nicknameHasChanged(it: String?) {
        nicknameInputLayout.error = it
    }

    override fun setNicknameHint(it: String?) {
        nicknameInputLayout.hint = it
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.error.unsubscribe(::nicknameHasChanged)
        viewModel.nickname.unsubscribe(::setNicknameHint)
    }
}

interface RegistrationNicknameInterface {
    fun nicknameHasChanged(it: String?)
    fun setNicknameHint(it: String?)
}