package com.example.rickandmortyxml.features.characters.domain.model


data class CharacterDomain(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: CharacterLocationDomain,
    val name: String,
    val origin: OriginDomain,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    companion object
}

data class CharacterLocationDomain(
    val name: String,
    val url: String
) {
    companion object
}

data class OriginDomain(
    val name: String,
    val url: String
) {
    companion object
}
