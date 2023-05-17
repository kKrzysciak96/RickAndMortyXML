package com.example.rickandmortyxml.features.locations.presentation.model

import com.example.rickandmortyxml.features.locations.domain.model.LocationDomain

data class LocationDisplayable(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
) {
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