package com.example.muvi.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.muvi.data.model.Genre

@BindingAdapter("genre")
fun loadText(view: TextView, list: List<Genre>) {}
