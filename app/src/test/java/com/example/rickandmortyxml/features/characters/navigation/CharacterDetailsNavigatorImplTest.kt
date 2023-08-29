package com.example.rickandmortyxml.features.characters.navigation

import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.navigation.FragmentNavigator
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class CharacterDetailsNavigatorImplTest {
    @Test
    fun `WHEN zoomPhoto is called THEN invoke navigateTo method of fragmentNavigator with appropriate argument to open zoomed photo dialog`() {
        //given
        val photoUrl = "dummy photo url"
        val slot = slot<Pair<String, String>>()
        val fragmentNavigator = mockk<FragmentNavigator>(relaxed = true)
        val characterDetailsNavigator: CharacterDetailsNavigator =
            CharacterDetailsNavigatorImpl(fragmentNavigator)
        //when
        characterDetailsNavigator.zoomPhoto(photoUrl)
        //then
        verify {
            fragmentNavigator.navigateTo(
                R.id.action_zoom_photo,
                capture(slot)
            )
        }
        slot.captured.second shouldBe photoUrl
    }

    @Test
    fun `WHEN goBack is called THEN invoke goBack method of fragmentNavigator`() {
        //given
        val fragmentNavigator = mockk<FragmentNavigator>(relaxed = true)
        val characterDetailsNavigator: CharacterDetailsNavigator =
            CharacterDetailsNavigatorImpl(fragmentNavigator)
        //when
        characterDetailsNavigator.goBack()
        //then
        verify {
            fragmentNavigator.goBack()
        }
    }
}