package com.example.rickandmortyxml.features.characters.navigation

import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.navigation.FragmentNavigator
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable
import com.example.rickandmortyxml.features.mockk.mock
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class CharacterNavigatorImplTest {

    @Test
    fun `WHEN openCharacterDetailsScreen is called THEN invoke navigateTo method of fragmentNavigator with appropriate argument to open character details screen`() {
        //given
        val character = CharacterDisplayable.mock()
        val slot = slot<Pair<String, CharacterDisplayable>>()
        val fragmentNavigator = mockk<FragmentNavigator>(relaxed = true)
        val characterNavigator: CharacterNavigator = CharacterNavigatorImpl(fragmentNavigator)
        //when
        characterNavigator.openCharacterDetailsScreen(character)
        //then
        verify {
            fragmentNavigator.navigateTo(
                R.id.action_navigate_from_character_screen_to_character_details_screen,
                capture(slot)
            )
        }
        slot.captured.second shouldBe character
    }

    @Test
    fun `WHEN goBack is called THEN invoke goBack method of fragmentNavigator`() {
        //given
        val fragmentNavigator = mockk<FragmentNavigator>(relaxed = true)
        val characterNavigator: CharacterNavigator = CharacterNavigatorImpl(fragmentNavigator)
        //when
        characterNavigator.goBack()
        //then
        verify {
            fragmentNavigator.goBack()
        }
    }
}