package com.example.organizeme

import android.media.Image
import android.widget.ImageView
import java.sql.Time

data class Task(
    val name: String,
    val description: String,
    val importance: Boolean,
    val urgent: Boolean,
    val routine: Int,
    val deadline: String,
    val status: ImageView
)