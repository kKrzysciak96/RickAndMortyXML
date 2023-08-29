package com.example.rickandmortyxml.features.locations.data.repository

import com.example.rickandmortyxml.core.api.RickAndMortyApi
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.exception.callOrThrow
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.features.locations.data.local.LocationCached
import com.example.rickandmortyxml.features.locations.data.local.LocationDao
import com.example.rickandmortyxml.features.locations.domain.LocationRepository
import com.example.rickandmortyxml.features.locations.domain.model.LocationDomain

class LocationRepositoryImpl(
    private val rickAndMortyApi: RickAndMortyApi,
    private val locationDao: LocationDao,
    private val networkStateProvider: NetworkStateProvider,
    private val errorWrapper: ErrorWrapper
) : LocationRepository {
    override suspend fun getAllLocations(): List<LocationDomain> {
        return if (networkStateProvider.isNetworkAvailable()) {
            callOrThrow(errorWrapper) {
                getLocationsFromRemote().also { saveLocationsToLocal(it) }
            }
        } else {
            getLocationsFromLocal()
        }
    }

    override suspend fun getMultipleLocations(): List<LocationDomain> {
        return emptyList()
    }

    private suspend fun saveLocationsToLocal(locations: List<LocationDomain>) {
        locations
            .map { LocationCached(it) }
            .toTypedArray()
            .let { locationDao.saveAllLocations(*it) }
    }

    private suspend fun getLocationsFromRemote(): List<LocationDomain> {
        return rickAndMortyApi.getAllLocations().results.map { it.toLocationDomain() }
    }

    private suspend fun getLocationsFromLocal(): List<LocationDomain> {
        return locationDao.getAllLocations().map { it.toLocationDomain() }
    }
}
