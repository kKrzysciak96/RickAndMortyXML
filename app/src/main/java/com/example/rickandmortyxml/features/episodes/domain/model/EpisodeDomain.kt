package com.example.rickandmortyxml.features.episodes.domain.model

data class EpisodeDomain(
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)
