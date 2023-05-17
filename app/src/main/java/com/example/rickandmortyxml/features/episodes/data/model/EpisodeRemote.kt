package com.example.rickandmortyxml.features.episodes.data.model

import com.example.rickandmortyxml.features.episodes.domain.model.EpisodeDomain
import com.google.gson.annotations.SerializedName


data class EpisodeRemote(
    @SerializedName("air_date") val airDate: String,
    @SerializedName("characters") val characters: List<String>,
    @SerializedName("created") val created: String,
    @SerializedName("episode") val episode: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
    fun toEpisodeDomain() = EpisodeDomain(
        airDate = airDate,
        characters = characters,
        created = created,
        episode = episode,
        id = id,
        name = name,
        url = url
    )
}