package com.example.rickandmortyxml.features.locations.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyxml.core.base.BaseViewModel
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.characters.domain.GetMultipleCharactersUseCase
import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable
import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable

class LocationDetailsViewModel(
    private val getMultipleCharactersUseCase: GetMultipleCharactersUseCase,
    errorMapper: ErrorMapper
) : BaseViewModel(errorMapper) {

    private val _location by lazy { MutableLiveData<LocationDisplayable>() }
    val location: LiveData<LocationDisplayable>
        get() = _location
    private val _characters by lazy {
        MutableLiveData<List<CharacterDomain>>().also {
            getAllCharacters(it)
        }
    }
    val characters: LiveData<List<CharacterDisplayable>>
        get() = _characters.map { characters -> characters.map { CharacterDisplayable(it) } }

    private fun getAllCharacters(mutableLiveData: MutableLiveData<List<CharacterDomain>>) {
        setPendingState()
        val params = location.value?.transformListOfCharactersToStringData() ?: ""
        getMultipleCharactersUseCase(params = params, scope = viewModelScope) { listResult ->
            setIdleState()
            listResult.onSuccess { mutableLiveData.value = it }
            listResult.onFailure { handleFailure(it) }
        }
    }

    fun onLocationPassed(locationDisplayable: LocationDisplayable) {
        _location.value = locationDisplayable
    }
}

fun LocationDisplayable.transformListOfCharactersToStringData(): String {
    val listOfCharacters: String = this.residents.fold("") { acc, s ->
        val characterID = s.substringAfterLast("/")
        if (acc.isBlank()) characterID else "$acc,$characterID"
    }
    return listOfCharacters
}