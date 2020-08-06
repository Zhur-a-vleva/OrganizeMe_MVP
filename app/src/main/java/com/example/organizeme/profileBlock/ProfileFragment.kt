package com.example.organizeme.profileBlock

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.organizeme.R

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    companion object {
        const val name = "ProfileFragment"
        private lateinit var context: Context
        private lateinit var email: String

        fun newInstance(cont: Context, em: String): ProfileFragment {
            email = em
            context = cont
            return ProfileFragment()
        }
    }

}
