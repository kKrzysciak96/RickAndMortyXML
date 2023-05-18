package com.example.rickandmortyxml.features.locations.presentation.whole

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyxml.core.base.BaseViewModel
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.locations.domain.GetLocationUseCase
import com.example.rickandmortyxml.features.locations.domain.model.LocationDomain
import com.example.rickandmortyxml.features.locations.navigation.LocationNavigator
import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable

class LocationViewModel(
    private val getLocationUseCase: GetLocationUseCase,
    private val locationNavigator: LocationNavigator,
    errorMapper: ErrorMapper
) : BaseViewModel(errorMapper) {

    private val _locations by lazy {
        MutableLiveData<List<LocationDomain>>().also {
            getAllLocations(
                it
            )
        }
    }
    val location: LiveData<List<LocationDisplayable>>
        get() = _locations.map { locations -> locations.map { LocationDisplayable(it) } }

    private fun getAllLocations(mutableLiveData: MutableLiveData<List<LocationDomain>>) {
        setPendingState()
        getLocationUseCase(params = Unit, scope = viewModelScope) { listResult ->
            setIdleState()
            listResult.onSuccess { mutableLiveData.value = it }
            listResult.onFailure { handleFailure(it) }
        }
    }

    fun onLocationClick(locationDisplayable: LocationDisplayable) {
        locationNavigator.openLocationDetailsScreen(locationDisplayable)
    }
}

