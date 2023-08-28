package com.example.rickandmortyxml.core.api.model

import com.example.rickandmortyxml.features.locations.data.model.LocationRemote
import com.google.gson.annotations.SerializedName

data class LocationsResponse(
    @SerializedName("info") val info: LocationRemoteInfo,
    @SerializedName("results") val results: List<LocationRemote>
) {
    companion object
}

data class LocationRemoteInfo(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
) {
    companion object
}

