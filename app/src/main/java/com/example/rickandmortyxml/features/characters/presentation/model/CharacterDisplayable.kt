package com.example.rickandmortyxml.features.characters.presentation.model

import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain
import com.example.rickandmortyxml.features.characters.domain.model.LocationDomain
import com.example.rickandmortyxml.features.characters.domain.model.OriginDomain


data class CharacterDisplayable(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationDisplayable,
    val name: String,
    val origin: OriginDisplayable,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    constructor(characterDomain: CharacterDomain) : this(
        created = characterDomain.created,
        episode = characterDomain.episode,
        gender = characterDomain.gender,
        id = characterDomain.id,
        image = characterDomain.image,
        location = LocationDisplayable(characterDomain.location),
        name = characterDomain.name,
        origin = OriginDisplayable(characterDomain.origin),
        species = characterDomain.species,
        status = characterDomain.status,
        type = characterDomain.type,
        url = characterDomain.url
    )
}

data class LocationDisplayable(
    val name: String,
    val url: String
) {
    constructor(locationDomain: LocationDomain) : this(
        name = locationDomain.name,
        url = locationDomain.url
    )
}

data class OriginDisplayable(
    val name: String,
    val url: String
) {
    constructor(originDomain: OriginDomain) : this(
        name = originDomain.name,
        url = originDomain.url
    )
}
