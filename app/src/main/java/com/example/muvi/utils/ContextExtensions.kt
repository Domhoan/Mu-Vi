package com.example.muvi.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(data: Any, duration: Int = Toast.LENGTH_SHORT) {
    val msg = when (data) {
        is String -> data
        is Int -> getString(data)
        else -> ""
    }
    Toast.makeText(this, msg, duration).show()
}
