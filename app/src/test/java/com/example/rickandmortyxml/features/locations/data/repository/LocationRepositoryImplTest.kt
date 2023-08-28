package com.example.rickandmortyxml.features.locations.data.repository

import com.example.rickandmortyxml.core.api.RickAndMortyApi
import com.example.rickandmortyxml.core.api.model.LocationsResponse
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.features.locations.data.local.LocationCached
import com.example.rickandmortyxml.features.locations.data.local.LocationDao
import com.example.rickandmortyxml.features.locations.domain.LocationRepository
import com.example.rickandmortyxml.features.mockk.mock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class LocationRepositoryImplTest {

    @Test
    fun `GIVEN network is connected WHEN locations request THEN fetch data from API`() {
        //given
        val api = mockk<RickAndMortyApi>() {
            coEvery { getAllLocations() } returns LocationsResponse.mock()
        }
        val dao = mockk<LocationDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider>() {
            every { isNetworkAvailable() } returns true
        }
        val errorWrapper = mockk<ErrorWrapper>()
        val repository: LocationRepository =
            LocationRepositoryImpl(api, dao, networkStateProvider, errorWrapper)
        //when
        runBlocking { repository.getAllLocations() }
        //then
        coVerify { api.getAllLocations() }
    }

    @Test
    fun `GIVEN network is connected AND successful data fetched WHEN locations request THEN save data to local database`() {
        //given
        val api = mockk<RickAndMortyApi>() {
            coEvery { getAllLocations() } returns LocationsResponse.mock()
        }
        val dao = mockk<LocationDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider>() {
            every { isNetworkAvailable() } returns true
        }
        val errorWrapper = mockk<ErrorWrapper>()
        val repository: LocationRepository =
            LocationRepositoryImpl(api, dao, networkStateProvider, errorWrapper)
        //when
        runBlocking { repository.getAllLocations() }
        //then
        coVerify { dao.saveAllLocations(*anyVararg()) }
    }

    @Test
    fun `GIVEN network is disconnected WHEN request remote data THEN fetch data from local database`() {
        //given
        val api = mockk<RickAndMortyApi>(relaxed = true)
        val dao = mockk<LocationDao>() {
            coEvery { getAllLocations() } returns listOf(
                LocationCached.mock(),
                LocationCached.mock(),
                LocationCached.mock()
            )
        }
        val networkStateProvider = mockk<NetworkStateProvider>() {
            every { isNetworkAvailable() } returns false
        }
        val errorWrapper = mockk<ErrorWrapper>()
        val repository: LocationRepository =
            LocationRepositoryImpl(api, dao, networkStateProvider, errorWrapper)
        //when
        runBlocking { repository.getAllLocations() }
        //then
        coVerify { dao.getAllLocations() }
    }
}