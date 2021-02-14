package com.task.noteapp.util.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageFromUrl")
fun setImage(
    view: ImageView,
    url: String?
) {
    view.loadImage(
        url
    )
}