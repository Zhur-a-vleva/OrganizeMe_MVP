package com.example.organizeme

import android.media.Image
import java.sql.Time

data class User(
    val name: String,
    val nickname: String,
    val description: String,
    val greyLine: String,
    val photo: Image,
    val friends: List<User>,
    val subscribers: List<User>,
    val doneTask: Int,
    val allTask: Int,
    val mail: String,
    val password: String,
    val tasks: List<Task>,
    val administrator: Boolean
)

data class Task(
    val name: String,
    val description: String,
    val importance: Boolean,
    val urgent: Boolean,
    val routine: Int,
    val deadline: Time,
    val status: Boolean
)

data class Marathon(
    val name: String,
    val description: String,
    val participators: List<User>,
    val image: Image
)

data class Problem(
    val title: String,
    val description: String
)