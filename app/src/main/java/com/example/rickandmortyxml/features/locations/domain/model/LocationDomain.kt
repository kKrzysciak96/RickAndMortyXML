package com.example.rickandmortyxml.features.locations.domain.model

data class LocationDomain(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)


