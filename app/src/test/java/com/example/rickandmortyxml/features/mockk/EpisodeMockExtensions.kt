package com.example.rickandmortyxml.features.mockk

import com.example.rickandmortyxml.core.api.model.EpisodeRemoteInfo
import com.example.rickandmortyxml.core.api.model.EpisodesResponse
import com.example.rickandmortyxml.features.episodes.data.local.EpisodeCached
import com.example.rickandmortyxml.features.episodes.data.model.EpisodeRemote
import com.example.rickandmortyxml.features.episodes.domain.model.EpisodeDomain
import com.example.rickandmortyxml.features.episodes.presentation.model.EpisodeDisplayable
import org.jetbrains.annotations.TestOnly


@TestOnly
fun EpisodeRemoteInfo.Companion.mock() =
    EpisodeRemoteInfo(count = 10, pages = 3, next = "next page url", prev = "previous page url")

@TestOnly
fun EpisodeRemote.Companion.mock() = EpisodeRemote(
    airDate = "airDate",
    characters = emptyList(),
    created = "created",
    episode = "episode",
    id = 1,
    name = "name",
    url = "url"
)

@TestOnly
fun EpisodesResponse.Companion.mock() = EpisodesResponse(
    info = EpisodeRemoteInfo.mock(),
    results = listOf(
        EpisodeRemote.mock(),
        EpisodeRemote.mock(),
        EpisodeRemote.mock()
    )
)

@TestOnly
fun EpisodeCached.Companion.mock() = EpisodeCached(
    airDate = "airDate",
    characters = emptyList(),
    created = "created",
    episode = "episode",
    id = 1,
    name = "name",
    url = "url"
)

@TestOnly
fun EpisodeDisplayable.Companion.mock() = EpisodeDisplayable(
    airDate = "airDate",
    characters = emptyList(),
    created = "created",
    episode = "episode",
    id = 1,
    name = "name",
    url = "url"
)

@TestOnly
fun EpisodeDomain.Companion.mock() = EpisodeDomain(
    airDate = "airDate",
    characters = emptyList(),
    created = "created",
    episode = "episode",
    id = 1,
    name = "name",
    url = "url"
)

