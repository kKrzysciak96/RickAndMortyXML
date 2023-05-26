package com.example.rickandmortyxml.features.characters.data.model

import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain
import com.example.rickandmortyxml.features.characters.domain.model.CharacterLocationDomain
import com.example.rickandmortyxml.features.characters.domain.model.OriginDomain
import com.google.gson.annotations.SerializedName


data class CharacterRemote(
    @SerializedName("created") val created: String,
    @SerializedName("episode") val episode: List<String>,
    @SerializedName("gender") val gender: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("location") val location: CharacterLocationRemote,
    @SerializedName("name") val name: String,
    @SerializedName("origin") val origin: OriginRemote,
    @SerializedName("species") val species: String,
    @SerializedName("status") val status: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
) {
    fun toCharacterDomain() = CharacterDomain(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location.toCharacterLocationDomain(),
        name = name,
        origin = origin.toOriginDomain(),
        species = species,
        status = status,
        type = type,
        url = url
    )
}

data class CharacterLocationRemote(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
    fun toCharacterLocationDomain() = CharacterLocationDomain(
        name = name,
        url = url
    )
}

data class OriginRemote(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
    fun toOriginDomain() = OriginDomain(
        name = name,
        url = url
    )
}