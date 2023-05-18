package com.example.rickandmortyxml.features.characters.navigation

import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable

interface CharacterNavigator {
    fun openCharacterDetailsScreen(characterDisplayable: CharacterDisplayable)
    fun goBack()

}