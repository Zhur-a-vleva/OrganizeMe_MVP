package ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.organizeme.R

class RegistrationFragment : Fragment(R.layout.registration_email_fragment) {

    companion object {

        const val name = "RegistrationFragment"
        private lateinit var context: Context

        fun newInstance(cont: Context): RegistrationFragment {
            context = cont
            return RegistrationFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}