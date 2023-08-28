package com.example.rickandmortyxml.features.locations.navigation

import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.navigation.FragmentNavigator
import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable
import com.example.rickandmortyxml.features.mockk.mock
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class LocationNavigatorImplTest {

    @Test
    fun `WHEN openLocationDetailsScreen is called THEN invoke navigateTo method of fragmentNavigator with appropriate argument to open location details screen`() {
        //given
        val location = LocationDisplayable.mock()
        val slot = slot<Pair<String, LocationDisplayable>>()
        val fragmentNavigator = mockk<FragmentNavigator>(relaxed = true)
        val navigator: LocationNavigator = LocationNavigatorImpl(fragmentNavigator)
        //when
        navigator.openLocationDetailsScreen(location)
        //then
        verify {
            fragmentNavigator.navigateTo(
                R.id.action_navigate_from_location_screen_to_location_details_screen,
                capture(slot)
            )
        }
        slot.captured.second shouldBe location
    }

    @Test
    fun `WHEN goBack is called THEN invoke goBack method of fragmentNavigator`() {
        //given
        val location = LocationDisplayable.mock()
        val fragmentNavigator = mockk<FragmentNavigator>(relaxed = true)
        val navigator: LocationNavigator = LocationNavigatorImpl(fragmentNavigator)
        //when
        navigator.goBack()
        //then
        verify {
            fragmentNavigator.goBack()
        }
    }
}