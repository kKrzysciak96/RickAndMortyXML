package com.example.rickandmortyxml.features.episodes.navigation

import com.example.rickandmortyxml.features.episodes.presentation.model.EpisodeDisplayable

interface EpisodeNavigator {
    fun openEpisodeDetailsScreen(episodeDisplayable: EpisodeDisplayable)
    fun goBack()
}