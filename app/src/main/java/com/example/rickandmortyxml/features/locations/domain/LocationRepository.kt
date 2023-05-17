package com.example.rickandmortyxml.features.locations.domain

import com.example.rickandmortyxml.features.locations.domain.model.LocationDomain

interface LocationRepository {
    suspend fun getAllLocations(): List<LocationDomain>
}