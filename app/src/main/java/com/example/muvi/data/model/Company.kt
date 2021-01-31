package com.example.muvi.data.model

import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("logo_path")
    val logo: String? = null
) : GeneralEntity {

    override fun areItemsTheSame(newItem: GeneralEntity): Boolean =
        newItem is Company && this.id == newItem.id

    override fun areContentsTheSame(newItem: GeneralEntity): Boolean =
        newItem is Company && this == newItem
}
