package com.example.rickandmortyxml.features.episodes.navigation

import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.navigation.FragmentNavigator
import com.example.rickandmortyxml.features.episodes.presentation.model.EpisodeDisplayable
import com.example.rickandmortyxml.features.mockk.mock
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class EpisodeNavigatorImplTest {

    @Test
    fun `WHEN openEpisodeDetailsScreen is called THEN invoke navigateTo method of fragmentNavigator with appropriate argument to open episode details screen`() {
        //given
        val fragmentNav = mockk<FragmentNavigator>(relaxed = true)
        val navigator: EpisodeNavigator = EpisodeNavigatorImpl(fragmentNav)
        val slot = slot<Pair<String, EpisodeDisplayable>>()
        val episode = EpisodeDisplayable.mock()
        //when
        navigator.openEpisodeDetailsScreen(episode)
        //then
        verify {
            fragmentNav.navigateTo(
                R.id.action_navigate_from_episode_screen_to_episode_details_screen,
                capture(slot)
            )
        }
        slot.captured.second shouldBe episode
    }

    @Test
    fun `WHEN goBack is called THEN invoke goBack method of FragmentNavigator`() {
        //given
        val fragmentNav = mockk<FragmentNavigator>(relaxed = true)
        val navigator: EpisodeNavigator = EpisodeNavigatorImpl(fragmentNav)
        //when
        navigator.goBack()
        //then
        verify {
            fragmentNav.goBack()
        }
    }
}