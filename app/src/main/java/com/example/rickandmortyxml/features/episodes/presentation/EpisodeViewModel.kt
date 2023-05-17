package com.example.rickandmortyxml.features.episodes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyxml.core.base.BaseViewModel
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.episodes.domain.GetEpisodeUseCase
import com.example.rickandmortyxml.features.episodes.domain.model.EpisodeDomain
import com.example.rickandmortyxml.features.episodes.presentation.model.EpisodeDisplayable

class EpisodeViewModel(private val getEpisodeUseCase: GetEpisodeUseCase, errorMapper: ErrorMapper) :
    BaseViewModel(errorMapper) {


    private val _episodes by lazy { MutableLiveData<List<EpisodeDomain>>().also { getAllEpisodes(it) } }
    val episodes: LiveData<List<EpisodeDisplayable>>
        get() = _episodes.map { episodesDomain -> episodesDomain.map { EpisodeDisplayable(it) } }

    private fun getAllEpisodes(mutableLiveData: MutableLiveData<List<EpisodeDomain>>) {
        setPendingState()
        getEpisodeUseCase(params = Unit, scope = viewModelScope) { listResult ->
            setIdleState()
            listResult.onSuccess { episodes ->
                mutableLiveData.value = episodes
            }
            listResult.onFailure { handleFailure(it) }
        }
    }
}


