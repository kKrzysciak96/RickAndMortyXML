package com.example.rickandmortyxml.features.episodes.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyxml.core.base.BaseViewModel
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.episodes.presentation.model.EpisodeDisplayable

class EpisodeDetailsViewModel(errorMapper: ErrorMapper) : BaseViewModel(errorMapper) {

    private val _episode by lazy { MutableLiveData<EpisodeDisplayable>() }
    val episode: LiveData<EpisodeDisplayable>
        get() = _episode

    fun onEpisodePassed(episodeDisplayable: EpisodeDisplayable) {
        _episode.value = episodeDisplayable
    }
}