package com.example.rickandmortyxml.features.locations.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortyxml.features.locations.domain.model.LocationDomain


@Entity
data class LocationCached(
    val created: String,
    val dimension: String,
    @PrimaryKey
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

    fun toLocationDomain() = LocationDomain(
        created = created,
        dimension = dimension,
        id = id,
        name = name,
        residents = residents,
        type = type,
        url = url
    )
}