package com.example.rickandmortyxml.features.episodes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortyxml.features.episodes.domain.model.EpisodeDomain

@Entity
data class EpisodeCached(
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val url: String
) {
    constructor(episodeDomain: EpisodeDomain) : this(
        airDate = episodeDomain.airDate,
        characters = episodeDomain.characters,
        created = episodeDomain.created,
        episode = episodeDomain.episode,
        id = episodeDomain.id,
        name = episodeDomain.name,
        url = episodeDomain.url
    )

    fun toEpisodeDomain() = EpisodeDomain(
        airDate = airDate,
        characters = characters,
        created = created,
        episode = episode,
        id = id,
        name = name,
        url = url
    )

    companion object
}
