package ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.organizeme.R

class SignInFragment : Fragment(R.layout.sign_in_fragment) {

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
    }
}