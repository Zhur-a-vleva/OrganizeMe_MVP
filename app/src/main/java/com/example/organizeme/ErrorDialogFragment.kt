package com.example.organizeme

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class ErrorDialogFragment : DialogFragment() {

    companion object {
        const val ERROR_TYPE_KEY: String = "ERROR_TYPE_KEY"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val view: View? = activity?.layoutInflater?.inflate(R.layout.error_dialog, null)

        val errorMessage: TextView? = view?.findViewById(R.id.error_message)

        val bundle = arguments
        if (bundle != null && bundle.containsKey(ERROR_TYPE_KEY)) {
            when (bundle.get(ERROR_TYPE_KEY)) {
                ErrorType.EMAIL_IS_NOT_REGISTERED.toString() -> {
                    errorMessage?.text = getString(R.string.email_is_not_registered)
                }
                ErrorType.PASSWORD_IS_NOT_CORRECT.toString() -> {
                    errorMessage?.text = getString(R.string.password_is_not_correct)
                }
                ErrorType.EMAIL_IS_NOT_WRITTEN.toString() -> {
                    errorMessage?.text = getString(R.string.email_is_not_written)
                }
                ErrorType.PASSWORD_IS_NOT_WRITTEN.toString() -> {
                    errorMessage?.text = getString(R.string.passwrod_is_not_written)
                }
                ErrorType.EMAIL_IS_NOT_CORRECT.toString() -> {
                    errorMessage?.text = getString(R.string.email_is_not_correct)
                }
            }
        }

        builder.setView(view)

        return builder.create()
    }
}