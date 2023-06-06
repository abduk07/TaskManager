package com.example.taskmanager.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

    fun ImageView.loadImage(url: String?) {
    Glide.with(this).load(url).into(this)

    fun Toast.showToast(toast: String?) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
    }
}