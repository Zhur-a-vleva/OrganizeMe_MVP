package signInBlock

import ErrorDialogFragment
import ErrorType
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.organizeme.R
import registrationBlock.RegistrationEmailFragment

class SignInFragment : Fragment(R.layout.sign_in_fragment), SignInView {

    private val presenter = SignInPresenter()

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

        val fragmentTransaction = fragmentManager?.beginTransaction()

        val emailInput: EditText = view.findViewById(R.id.email_input)
        val passwordInput: EditText = view.findViewById(R.id.password_input)
        val forgotPassword: TextView = view.findViewById(R.id.forgot_password)
        val signInButton: Button = view.findViewById(R.id.sign_in)
        val signUp: TextView = view.findViewById(R.id.sign_up)

        //TODO: email correct listener. Добавить ввод с буфера обмена
        //TODO: password correct listener
        //TODO: forgot password listener

        forgotPassword.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
            val dialog = alertDialog.create()

            dialog.setView(layoutInflater.inflate(R.layout.forgot_password_dialog, null))



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
                RegistrationEmailFragment.newInstance(
                    Companion.context
                )
            )
                ?.commit()
            fragmentTransaction?.addToBackStack(RegistrationEmailFragment.name)
        }
    }

    override fun signInFailed(error: ErrorType) {
        var bundle = Bundle()
        bundle.putString(ErrorDialogFragment.ERROR_TYPE_KEY, error.toString())
        val errorMessage: ErrorDialogFragment = ErrorDialogFragment()
        errorMessage.arguments = bundle
        fragmentManager?.let { errorMessage.show(it, errorMessage.tag) }
    }

    override fun showProfile(data: Any) {
        TODO("Отобразить данные и специфицировать тип")
    }
}

interface SignInView {
    fun signInFailed(error: ErrorType)
    fun showProfile(data: Any)
}