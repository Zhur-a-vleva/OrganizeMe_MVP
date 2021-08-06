package com.example.organizeme.calendar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.organizeme.R
import com.example.organizeme.Task
import kotlinx.android.synthetic.main.calendar.*

class CalendarView : Fragment(R.layout.calendar){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tasks = mutableListOf<Task>()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter(tasks)

        addTask.setOnClickListener {
            //TODO
        }
    }

}