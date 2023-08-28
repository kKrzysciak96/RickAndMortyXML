package com.example.rickandmortyxml.features.characters.data.repository

import com.example.rickandmortyxml.core.api.RickAndMortyApi
import com.example.rickandmortyxml.core.api.model.CharactersResponse
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.features.characters.data.local.CharacterCached
import com.example.rickandmortyxml.features.characters.data.local.CharacterDao
import com.example.rickandmortyxml.features.characters.domain.CharacterRepository
import com.example.rickandmortyxml.features.mockk.mock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class CharacterRepositoryImplTest {

    @Test
    fun `GIVEN network is connected WHEN characters request THEN fetch data from API`() {
        //given
        val api = mockk<RickAndMortyApi>() {
            coEvery { getAllCharacters() } returns CharactersResponse.mock()
        }
        val dao = mockk<CharacterDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider>() {
            every { isNetworkAvailable() } returns true
        }
        val errorWrapper = mockk<ErrorWrapper>(relaxed = true)
        val repository: CharacterRepository =
            CharacterRepositoryImpl(api, dao, networkStateProvider, errorWrapper)
        //when
        runBlocking { repository.getAllCharacters() }
        //then
        coVerify { api.getAllCharacters() }
    }

    @Test
    fun `GIVEN network is connected AND successful data fetched WHEN characters request THEN save data to local database`() {
        //given
        val api = mockk<RickAndMortyApi>() {
            coEvery { getAllCharacters() } returns CharactersResponse.mock()
        }
        val dao = mockk<CharacterDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider>() {
            every { isNetworkAvailable() } returns true
        }
        val errorWrapper = mockk<ErrorWrapper>(relaxed = true)
        val repository: CharacterRepository =
            CharacterRepositoryImpl(api, dao, networkStateProvider, errorWrapper)
        //when
        runBlocking { repository.getAllCharacters() }
        //then
        coVerify { dao.saveAllCharacters(*anyVararg()) }
    }

    @Test
    fun `GIVEN network is disconnected WHEN characters request THEN fetch data from local database`() {
        //given
        val api = mockk<RickAndMortyApi>(relaxed = true)
        val dao = mockk<CharacterDao>() {
            coEvery { getAllCharacters() } returns listOf(
                CharacterCached.mock(),
                CharacterCached.mock(),
                CharacterCached.mock()
            )
        }
        val networkStateProvider = mockk<NetworkStateProvider>() {
            every { isNetworkAvailable() } returns false
        }
        val errorWrapper = mockk<ErrorWrapper>(relaxed = true)
        val repository: CharacterRepository =
            CharacterRepositoryImpl(api, dao, networkStateProvider, errorWrapper)
        //when
        runBlocking { repository.getAllCharacters() }
        //then
        coVerify { dao.getAllCharacters() }
    }
}