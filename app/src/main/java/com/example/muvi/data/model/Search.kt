package com.example.muvi.data.model

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("id")
    val id: Int,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("poster_path")
    val poster: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("profile_path")
    val profile: String? = null
) : GeneralEntity {

    constructor(search: Search) : this(
        id = search.id,
        mediaType = search.mediaType,
        title = search.title,
        poster = search.poster,
        name = search.name,
        profile = search.profile
    )

    override fun areItemsTheSame(newItem: GeneralEntity): Boolean =
        newItem is Search && this.id == newItem.id

    override fun areContentsTheSame(newItem: GeneralEntity): Boolean =
        newItem is Search && this == newItem

    companion object {
        const val MOVIE = "movie"
        const val PERSON = "person"
    }
}
