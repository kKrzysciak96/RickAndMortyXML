package com.example.rickandmortyxml.core.api.model

import com.example.rickandmortyxml.features.characters.data.model.CharacterRemote
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("info") val info: CharacterRemoteInfo,
    @SerializedName("results") val results: List<CharacterRemote>
)

data class CharacterRemoteInfo(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String,
    @SerializedName("prev") val prev: String?
)