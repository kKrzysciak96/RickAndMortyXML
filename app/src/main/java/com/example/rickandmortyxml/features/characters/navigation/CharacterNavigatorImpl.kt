package com.example.rickandmortyxml.features.characters.navigation

import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.navigation.FragmentNavigator
import com.example.rickandmortyxml.features.characters.presentation.details.CharacterDetailsFragment
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable

class CharacterNavigatorImpl(private val fragmentNavigator: FragmentNavigator) :
    CharacterNavigator {
    override fun openCharacterDetailsScreen(characterDisplayable: CharacterDisplayable) {
        val pair = CharacterDetailsFragment.CHARACTER_DETAILS_BUNDLE_KEY to characterDisplayable
        fragmentNavigator.navigateTo(
            R.id.action_navigate_from_character_screen_to_character_details_screen,
            pair
        )
    }

    override fun goBack() {
        fragmentNavigator.goBack()
    }
}