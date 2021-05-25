package com.example.organizeme.profileBlock

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.organizeme.R
import kotlinx.android.synthetic.main.profile_view.*

class ProfileView : Fragment(R.layout.profile_view) {

    private lateinit var navigationController: NavController
    private val viewModel: ProfileViewModel by viewModels()

    //TODO(OnBackListener "Do you want to exit?")
    //TODO(get information from Room)
    //TODO(add shadow to bottom navigation view)
    //TODO(set title to topAppBar)
    //TODO(change from settings icon to subscribe icon)
    //TODO(set the font and alignment to title)
    //TODO(get name and surname, photo, status, subscribers and subscriptions)
    //TODO(set circle image)
    //TODO(calculate efficiency and done task)
    //TODO(get tasks from server)
    //TODO(set onDoneIconListener)
    //TODO(set onEditTaskIconListener)
    


    companion object {
        const val name = "ProfileView"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navigationController = NavHostFragment.findNavController(this)

        bottomNavigation.selectedItemId = R.id.profile
        bottomNavigation.setOnNavigationItemSelectedListener { icon ->
            when (icon.itemId) {
                R.id.home -> {
                    //TODO
                    true
                }
                R.id.marathons -> {
                    //TODO
                    true
                }
                R.id.task -> {
                    //TODO
                    true
                }
                R.id.plans -> {
                    //TODO
                    true
                }
                R.id.profile -> {
                    true
                }
                else -> false
            }
        }

        topToolBar.setNavigationOnClickListener {
            //TODO(handle navigation icon pressed)
        }

        /* topToolBar.setOnMenuItemClickListener { icon ->

        }

         */

        //TODO
        //When time is 12 pm we post  today's plan
        val cardView2 = View.inflate(context, R.layout.task, null)
        scrollView.addView(cardView2)

        val cardView3 = View.inflate(context, R.layout.task, null)
        scrollView.addView(cardView3)

        val cardView4 = View.inflate(context, R.layout.task, null)
        scrollView.addView(cardView4)
    }
}
