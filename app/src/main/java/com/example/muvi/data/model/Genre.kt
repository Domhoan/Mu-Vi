package com.example.muvi.data.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    val image: String? = null
) : GeneralEntity {

    override fun areItemsTheSame(newItem: GeneralEntity): Boolean =
        newItem is Genre && this.id == newItem.id

    override fun areContentsTheSame(newItem: GeneralEntity): Boolean =
        newItem is Genre && this == newItem
}
