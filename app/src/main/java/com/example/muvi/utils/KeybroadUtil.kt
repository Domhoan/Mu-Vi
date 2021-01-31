package com.example.muvi.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView


fun hideSoftInput(activity: Activity) {
    var view: View? = activity.currentFocus
    if (view == null) view = View(activity)
    view.clearFocus()
    val imm: InputMethodManager = activity
        .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun showSoftInput(edit: SearchView) {
    edit.isFocusable = true
    edit.isFocusableInTouchMode = true
    edit.requestFocus()
    val imm = edit.context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(edit, 0)
}
