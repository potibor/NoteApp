package com.task.noteapp.testutil.databinding

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