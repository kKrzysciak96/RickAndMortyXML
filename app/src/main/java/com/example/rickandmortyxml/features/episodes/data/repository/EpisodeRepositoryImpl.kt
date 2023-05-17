package com.example.rickandmortyxml.features.episodes.data.repository

import com.example.rickandmortyxml.core.api.RickAndMortyApi
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.exception.callOrThrow
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.features.episodes.data.local.EpisodeCached
import com.example.rickandmortyxml.features.episodes.data.local.EpisodeDao
import com.example.rickandmortyxml.features.episodes.domain.EpisodeRepository
import com.example.rickandmortyxml.features.episodes.domain.model.EpisodeDomain

class EpisodeRepositoryImpl(
    private val rickAndMortyApi: RickAndMortyApi,
    private val episodeDao: EpisodeDao,
    private val networkStateProvider: NetworkStateProvider,
    private val errorWrapper: ErrorWrapper
) : EpisodeRepository {
    override suspend fun getEpisodes(): List<EpisodeDomain> {
        return if (networkStateProvider.isNetworkAvailable()) {
            callOrThrow(errorWrapper) {
                getEpisodesFromRemote().also { saveEpisodesToLocal(it) }
            }
        } else {
            getEpisodesFromLocal()
        }
    }

    private suspend fun saveEpisodesToLocal(episodes: List<EpisodeDomain>) {
        episodes
            .map { EpisodeCached(it) }
            .toTypedArray()
            .let { episodeDao.saveAllEpisodes(*it) }
    }

    private suspend fun getEpisodesFromRemote(): List<EpisodeDomain> {
        return rickAndMortyApi.getAllEpisodes().results.map { it.toEpisodeDomain() }
    }


    private suspend fun getEpisodesFromLocal(): List<EpisodeDomain> {
        return episodeDao.getAllEpisodes().map { it.toEpisodeDomain() }
    }


}

