package com.example.rickandmortyxml.features.locations.navigation

import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.navigation.FragmentNavigator
import com.example.rickandmortyxml.features.locations.presentation.details.LocationDetailsFragment
import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable

class LocationNavigatorImpl(private val fragmentNavigator: FragmentNavigator) : LocationNavigator {
    override fun openLocationDetailsScreen(locationDisplayable: LocationDisplayable) {
        val pair =
            LocationDetailsFragment.LOCATION_DETAILS_BUNDLE_KEY to locationDisplayable
        fragmentNavigator.navigateTo(
            R.id.action_navigate_from_location_screen_to_location_details_screen,
            pair
        )
    }

    override fun goBack() {
        fragmentNavigator.goBack()
    }
}