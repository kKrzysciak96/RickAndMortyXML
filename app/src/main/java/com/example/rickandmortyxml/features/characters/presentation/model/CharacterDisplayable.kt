package com.example.rickandmortyxml.features.characters.presentation.model

import android.os.Parcelable
import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain
import com.example.rickandmortyxml.features.characters.domain.model.CharacterLocationDomain
import com.example.rickandmortyxml.features.characters.domain.model.OriginDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDisplayable(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: CharacterLocationDisplayable,
    val name: String,
    val origin: OriginDisplayable,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) : Parcelable {
    constructor(characterDomain: CharacterDomain) : this(
        created = characterDomain.created,
        episode = characterDomain.episode,
        gender = characterDomain.gender,
        id = characterDomain.id,
        image = characterDomain.image,
        location = CharacterLocationDisplayable(characterDomain.location),
        name = characterDomain.name,
        origin = OriginDisplayable(characterDomain.origin),
        species = characterDomain.species,
        status = characterDomain.status,
        type = characterDomain.type,
        url = characterDomain.url
    )
    companion object
}

@Parcelize
data class CharacterLocationDisplayable(
    val name: String,
    val url: String
) : Parcelable {
    constructor(locationDomain: CharacterLocationDomain) : this(
        name = locationDomain.name,
        url = locationDomain.url
    )

    companion object
}

@Parcelize
data class OriginDisplayable(
    val name: String,
    val url: String
) : Parcelable {
    constructor(originDomain: OriginDomain) : this(
        name = originDomain.name,
        url = originDomain.url
    )

    companion object
}
