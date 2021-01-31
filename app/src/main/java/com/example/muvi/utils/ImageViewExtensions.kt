package com.example.muvi.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.muvi.R

fun ImageView.loadImage(urlImage: String) {
    Glide.with(context)
        .load(urlImage)
        .error(R.drawable.ic_movie_error)
        .into(this)
}

fun ImageView.loadImage(drawable: Int) {
    Glide.with(context)
        .load(drawable)
        .error(R.drawable.ic_movie_error)
        .into(this)
}

fun ImageView.loadImageCircle(urlImage: String) {
    Glide.with(context)
        .load(urlImage)
        .apply(RequestOptions.circleCropTransform())
        .error(R.drawable.ic_movie_error)
        .into(this)
}
