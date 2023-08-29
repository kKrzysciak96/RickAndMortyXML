package com.example.rickandmortyxml.features.episodes.domain

import com.example.rickandmortyxml.features.episodes.domain.model.EpisodeDomain

interface EpisodeRepository {
    suspend fun getEpisodes(): List<EpisodeDomain>
    suspend fun getMultipleEpisodes(): List<EpisodeDomain>

}