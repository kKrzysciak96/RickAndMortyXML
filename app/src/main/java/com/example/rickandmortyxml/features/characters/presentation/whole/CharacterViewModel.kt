package com.example.rickandmortyxml.features.characters.presentation.whole


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyxml.core.base.BaseViewModel
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.characters.domain.GetCharacterUseCase
import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain
import com.example.rickandmortyxml.features.characters.navigation.CharacterNavigator
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable

class CharacterViewModel(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val characterNavigator: CharacterNavigator,
    errorMapper: ErrorMapper,

    ) : BaseViewModel(errorMapper) {

    private val _characters by lazy {
        MutableLiveData<List<CharacterDomain>>().also {
            getAllCharacters(
                it
            )
        }
    }

    val characters: LiveData<List<CharacterDisplayable>>
        get() = _characters.map { characters -> characters.map { CharacterDisplayable(it) } }

    private fun getAllCharacters(mutableLiveData: MutableLiveData<List<CharacterDomain>>) {
        setPendingState()
        getCharacterUseCase(params = Unit, scope = viewModelScope) { listResult ->
            setIdleState()
            listResult.onSuccess { mutableLiveData.value = it }
            listResult.onFailure { handleFailure(it) }
        }
    }

    fun onCharacterClick(characterDisplayable: CharacterDisplayable) {
        characterNavigator.openCharacterDetailsScreen(characterDisplayable)
    }
}
