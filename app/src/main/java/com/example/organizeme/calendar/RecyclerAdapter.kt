package com.example.organizeme.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.organizeme.R
import com.example.organizeme.Task

class RecyclerAdapter(private val tasks : MutableList<Task>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView.findViewById(R.id.nameOfTask)
        val description: TextView? = itemView.findViewById(R.id.nameOfTask)
        val deadline: TextView? = itemView.findViewById(R.id.date)
        var status: ImageView? = itemView.findViewById(R.id.taskStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.task, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name?.text = tasks[position].name
        holder.description?.text = tasks[position].description
        holder.deadline?.text = tasks[position].deadline
        holder.status = tasks[position].status
    }

    override fun getItemCount() = tasks.size
}