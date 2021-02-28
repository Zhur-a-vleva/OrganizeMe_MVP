package com.example.organizeme.profileBlock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.organizeme.R

class ProfileFragment : Fragment() {

    //TODO(OnBackListener "Do you want to exit?")

    private lateinit var email: String

    companion object {
        const val name = "ProfileFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

}
