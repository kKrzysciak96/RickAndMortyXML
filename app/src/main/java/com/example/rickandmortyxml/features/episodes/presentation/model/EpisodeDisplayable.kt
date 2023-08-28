package com.example.rickandmortyxml.features.episodes.presentation.model

import android.os.Parcelable
import com.example.rickandmortyxml.features.episodes.domain.model.EpisodeDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeDisplayable(
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
) : Parcelable {
    constructor(episodeDomain: EpisodeDomain) : this(
        airDate = episodeDomain.airDate,
        characters = episodeDomain.characters,
        created = episodeDomain.created,
        episode = episodeDomain.episode,
        id = episodeDomain.id,
        name = episodeDomain.name,
        url = episodeDomain.url
    )
    companion object
}
