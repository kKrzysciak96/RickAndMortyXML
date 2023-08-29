package com.example.rickandmortyxml.features.characters.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyxml.core.base.BaseViewModel
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.characters.navigation.CharacterDetailsNavigator
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable

class CharacterDetailsViewModel(
    private val characterDetailsNavigator: CharacterDetailsNavigator,
    errorMapper: ErrorMapper
) : BaseViewModel(errorMapper) {
    private val _character by lazy { MutableLiveData<CharacterDisplayable>() }
    val character: LiveData<CharacterDisplayable>
        get() = _character

    fun onCharacterPassed(characterDisplayable: CharacterDisplayable) {
        _character.value = characterDisplayable
    }

    fun zoomPhotoOnClick() {
        character.value?.image?.let { characterDetailsNavigator.zoomPhoto(it) }
    }
}