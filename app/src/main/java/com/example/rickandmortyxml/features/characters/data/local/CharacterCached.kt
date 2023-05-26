package com.example.rickandmortyxml.features.characters.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain
import com.example.rickandmortyxml.features.characters.domain.model.CharacterLocationDomain
import com.example.rickandmortyxml.features.characters.domain.model.OriginDomain


@Entity
data class CharacterCached(
    val created: String,
    val episode: List<String>,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    @Embedded("CharacterLocationCached")
    val location: CharacterLocationCached,
    val name: String,
    @Embedded("OriginCached")
    val origin: OriginCached,
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
        location = CharacterLocationCached(characterDomain.location),
        name = characterDomain.name,
        origin = OriginCached(characterDomain.origin),
        species = characterDomain.species,
        status = characterDomain.status,
        type = characterDomain.type,
        url = characterDomain.url
    )

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

data class CharacterLocationCached(
    val name: String,
    val url: String
) {
    constructor(locationDomain: CharacterLocationDomain) : this(
        name = locationDomain.name,
        url = locationDomain.url
    )

    fun toCharacterLocationDomain() = CharacterLocationDomain(
        name = name,
        url = url
    )
}

data class OriginCached(
    val name: String,
    val url: String
) {
    constructor(originDomain: OriginDomain) : this(
        name = originDomain.name,
        url = originDomain.url
    )

    fun toOriginDomain() = OriginDomain(
        name = name,
        url = url
    )
}




