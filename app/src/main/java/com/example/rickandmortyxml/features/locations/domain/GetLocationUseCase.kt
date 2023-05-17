package com.example.rickandmortyxml.features.locations.domain

import com.example.rickandmortyxml.core.base.BaseUseCase
import com.example.rickandmortyxml.features.locations.domain.model.LocationDomain

class GetLocationUseCase(private val locationRepository: LocationRepository) :
    BaseUseCase<Unit, List<LocationDomain>>() {
    override suspend fun action(params: Unit): List<LocationDomain> {
        return locationRepository.getAllLocations()
    }
}