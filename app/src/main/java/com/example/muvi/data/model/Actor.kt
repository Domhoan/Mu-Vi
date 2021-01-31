package com.example.muvi.data.model

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("id")
    val castId: Int,
    var castMovieId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val avatar: String?,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("place_of_birth")
    val place: String,
    @SerializedName("biography")
    val biography: String
) : GeneralEntity {

    constructor(actor: Actor) : this(
        castId = actor.castId,
        castMovieId = actor.castMovieId,
        name = actor.name,
        avatar = actor.avatar,
        birthday = actor.birthday,
        place = actor.place,
        biography = actor.biography
    )

    override fun areItemsTheSame(newItem: GeneralEntity): Boolean =
        newItem is Actor && this.castId == newItem.castId

    override fun areContentsTheSame(newItem: GeneralEntity): Boolean =
        newItem is Actor && this == newItem
}
