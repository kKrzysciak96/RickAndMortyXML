package com.example.rickandmortyxml.features.locations.presentation.model

import android.os.Parcelable
import com.example.rickandmortyxml.features.locations.domain.model.LocationDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDisplayable(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
) : Parcelable {
    constructor(locationDomain: LocationDomain) : this(
        created = locationDomain.created,
        dimension = locationDomain.dimension,
        id = locationDomain.id,
        name = locationDomain.name,
        residents = locationDomain.residents,
        type = locationDomain.type,
        url = locationDomain.url
    )
}