package com.example.rickandmortyxml.features.episodes.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyxml.core.base.BaseViewModel
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.characters.domain.GetMultipleCharactersUseCase
import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable
import com.example.rickandmortyxml.features.episodes.presentation.model.EpisodeDisplayable

class EpisodeDetailsViewModel(
    private val getMultipleCharactersUseCase: GetMultipleCharactersUseCase,
    errorMapper: ErrorMapper
) : BaseViewModel(errorMapper) {

    private val _episode by lazy { MutableLiveData<EpisodeDisplayable>() }
    val episode: LiveData<EpisodeDisplayable>
        get() = _episode

    private val _characters by lazy {
        MutableLiveData<List<CharacterDomain>>().also {
            getAllCharacters(it)
        }
    }

    val characters: LiveData<List<CharacterDisplayable>>
        get() = _characters.map { characters -> characters.map { CharacterDisplayable(it) } }

    private fun getAllCharacters(mutableLiveData: MutableLiveData<List<CharacterDomain>>) {
        setPendingState()
        val params = episode.value?.transformListOfCharactersToStringData() ?: ""
        getMultipleCharactersUseCase(params = params, scope = viewModelScope) { listResult ->
            setIdleState()
            listResult.onSuccess { mutableLiveData.value = it }
            listResult.onFailure { handleFailure(it) }
        }
    }

    fun onEpisodePassed(episodeDisplayable: EpisodeDisplayable) {
        _episode.value = episodeDisplayable
    }
}

fun EpisodeDisplayable.transformListOfCharactersToStringData(): String {
    val listOfCharacters: String = this.characters.fold("") { acc, s ->
        val characterID = s.substringAfterLast("/")
        if (acc.isBlank()) characterID else "$acc,$characterID"
    }
    return listOfCharacters
}
