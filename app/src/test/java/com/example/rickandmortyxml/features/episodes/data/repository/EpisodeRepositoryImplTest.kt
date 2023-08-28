package com.example.rickandmortyxml.features.episodes.data.repository

import com.example.rickandmortyxml.core.api.RickAndMortyApi
import com.example.rickandmortyxml.core.api.model.EpisodesResponse
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.features.episodes.data.local.EpisodeCached
import com.example.rickandmortyxml.features.episodes.data.local.EpisodeDao
import com.example.rickandmortyxml.features.episodes.domain.EpisodeRepository
import com.example.rickandmortyxml.features.mockk.mock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class EpisodeRepositoryImplTest {

    @Test
    fun `GIVEN network is connected WHEN episodes request THEN fetch data from API`() {
        //given
        val api = mockk<RickAndMortyApi> {
            coEvery { getAllEpisodes() } returns EpisodesResponse.mock()
        }
        val dao = mockk<EpisodeDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider>() {
            every { isNetworkAvailable() } returns true
        }
        val errorWrapper = mockk<ErrorWrapper>(relaxed = true)
        val repository: EpisodeRepository =
            EpisodeRepositoryImpl(api, dao, networkStateProvider, errorWrapper)
        //when
        runBlocking { repository.getEpisodes() }
        //then
        coVerify { api.getAllEpisodes() }
    }

    @Test
    fun `GIVEN network is connected AND successful data fetched WHEN episodes request THEN save data to local`() {
        //given
        val api = mockk<RickAndMortyApi> {
            coEvery { getAllEpisodes() } returns EpisodesResponse.mock()
        }
        val dao = mockk<EpisodeDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider>() {
            every { isNetworkAvailable() } returns true
        }
        val errorWrapper = mockk<ErrorWrapper>(relaxed = true)
        val repository: EpisodeRepository =
            EpisodeRepositoryImpl(api, dao, networkStateProvider, errorWrapper)
        //when
        runBlocking { repository.getEpisodes() }
        //then
        coVerify { dao.saveAllEpisodes(*anyVararg()) }
    }

    @Test
    fun `GIVEN network is disconnected WHEN episodes request THEN fetch data from local database`() {
        //given
        val api = mockk<RickAndMortyApi>(relaxed = true)
        val dao = mockk<EpisodeDao>() {
            coEvery { getAllEpisodes() } returns listOf(
                EpisodeCached.mock(),
                EpisodeCached.mock(),
                EpisodeCached.mock()
            )
        }
        val networkStateProvider = mockk<NetworkStateProvider>() {
            every { isNetworkAvailable() } returns false
        }
        val errorWrapper = mockk<ErrorWrapper>(relaxed = true)
        val repository: EpisodeRepository =
            EpisodeRepositoryImpl(api, dao, networkStateProvider, errorWrapper)
        //when
        runBlocking { repository.getEpisodes() }
        //then
        coVerify { dao.getAllEpisodes() }
    }
}