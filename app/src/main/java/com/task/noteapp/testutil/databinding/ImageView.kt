package com.task.noteapp.testutil.databinding

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) = Glide.with(this.context)
    .load(url)
    .into(this)