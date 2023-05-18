package com.example.rickandmortyxml.features.locations.navigation

import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable

interface LocationNavigator {
    fun openLocationDetailsScreen(locationDisplayable: LocationDisplayable)
    fun goBack()
}