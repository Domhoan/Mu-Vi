package com.example.muvi.base

import androidx.recyclerview.widget.DiffUtil
import com.example.muvi.data.model.GeneralEntity

class BaseDiffUtil<T : GeneralEntity> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}
