package com.example.rickandmortyxml.features.episodes.navigation

import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.navigation.FragmentNavigator
import com.example.rickandmortyxml.features.episodes.presentation.details.EpisodeDetailsFragment
import com.example.rickandmortyxml.features.episodes.presentation.model.EpisodeDisplayable

class EpisodeNavigatorImpl(private val fragmentNavigator: FragmentNavigator) : EpisodeNavigator {
    override fun openEpisodeDetailsScreen(episodeDisplayable: EpisodeDisplayable) {
        val pair =
            EpisodeDetailsFragment.EPISODE_DETAILS_BUNDLE_KEY to episodeDisplayable
        fragmentNavigator.navigateTo(
            R.id.action_navigate_from_episode_screen_to_episode_details_screen,
            pair
        )
    }

    override fun goBack() {
        fragmentNavigator.goBack()
    }
}