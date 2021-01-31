package com.example.muvi.data.model

interface GeneralEntity {
    fun areItemsTheSame(newItem: GeneralEntity): Boolean
    fun areContentsTheSame(newItem: GeneralEntity): Boolean
}
