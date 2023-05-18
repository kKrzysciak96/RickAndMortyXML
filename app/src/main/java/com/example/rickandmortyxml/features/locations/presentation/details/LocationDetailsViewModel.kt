package com.example.rickandmortyxml.features.locations.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyxml.core.base.BaseViewModel
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable

class LocationDetailsViewModel(errorMapper: ErrorMapper) : BaseViewModel(errorMapper) {

    private val _location by lazy { MutableLiveData<LocationDisplayable>() }
    val location: LiveData<LocationDisplayable>
        get() = _location

    fun onLocationPassed(locationDisplayable: LocationDisplayable) {
        _location.value = locationDisplayable
    }

}