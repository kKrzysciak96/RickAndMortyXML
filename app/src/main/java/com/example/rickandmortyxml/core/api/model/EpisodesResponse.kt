package com.example.rickandmortyxml.core.api.model

import com.example.rickandmortyxml.features.episodes.data.model.EpisodeRemote
import com.google.gson.annotations.SerializedName

data class EpisodesResponse(
    @SerializedName("info") val info: EpisodeRemoteInfo,
    @SerializedName("results") val results: List<EpisodeRemote>
) {
    companion object
}

data class EpisodeRemoteInfo(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
) {
    companion object
}
