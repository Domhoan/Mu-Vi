package com.example.muvi.utils

import android.view.View
import com.example.muvi.R
import com.google.android.material.snackbar.Snackbar

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.make(message: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, length).setAnchorView(R.id.navView).show()
}
