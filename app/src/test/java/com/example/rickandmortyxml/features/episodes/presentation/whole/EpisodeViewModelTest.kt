package com.example.rickandmortyxml.features.episodes.presentation.whole

import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyxml.core.base.UiState
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.episodes.domain.GetEpisodeUseCase
import com.example.rickandmortyxml.features.episodes.domain.model.EpisodeDomain
import com.example.rickandmortyxml.features.episodes.navigation.EpisodeNavigator
import com.example.rickandmortyxml.features.episodes.presentation.model.EpisodeDisplayable
import com.example.rickandmortyxml.features.mockk.mock
import com.example.rickandmortyxml.features.utils.ViewModelTest
import com.example.rickandmortyxml.features.utils.getOrAwaitValue
import com.example.rickandmortyxml.features.utils.observeForTesting
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class EpisodeViewModelTest : ViewModelTest() {

    @Test
    fun `When episode is clicked THEN open episode details screen`() {
        //given
        val useCase = mockk<GetEpisodeUseCase>(relaxed = true)
        val episode = EpisodeDisplayable.mock()
        val navigator = mockk<EpisodeNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = EpisodeViewModel(useCase, navigator, errorMapper)
        //when
        viewModel.onEpisodeClick(episode)
        //then
        verify { navigator.openEpisodeDetailsScreen(episode) }
    }

    @Test
    fun `WHEN episodes live data is observed THEN set pending state`() {
        //given
        val useCase = mockk<GetEpisodeUseCase>(relaxed = true)
        val navigator = mockk<EpisodeNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = EpisodeViewModel(useCase, navigator, errorMapper)
        //when
        viewModel.episodes.observeForTesting()
        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.PendingState
    }

    @Test
    fun `WHEN episodes live data is observed THEN invoke use case to get episodes`() {
        //given
        val useCase = mockk<GetEpisodeUseCase>(relaxed = true)
        val navigator = mockk<EpisodeNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = EpisodeViewModel(useCase, navigator, errorMapper)
        //when
        viewModel.episodes.observeForTesting()
        //then
        verify { useCase(Unit, viewModel.viewModelScope, any(), any()) }
    }

    @Test
    fun `GIVEN use case result is success WHEN episodes live data is observed THEN set idle state AND set result in live data`() {
        //given
        val episodes = listOf(EpisodeDomain.mock(), EpisodeDomain.mock(), EpisodeDomain.mock())
        val navigator = mockk<EpisodeNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val useCase = mockk<GetEpisodeUseCase>() {
            every { this@mockk(Unit, any(), any(), any()) } answers {
                lastArg<(Result<List<EpisodeDomain>>) -> Unit>()(Result.success(episodes))
            }
        }
        val viewModel = EpisodeViewModel(useCase, navigator, errorMapper)
        //when
        viewModel.episodes.observeForTesting()
        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.IdleState
        viewModel.episodes.getOrAwaitValue().forEachIndexed { index, episodeDisplayable ->
            val episode = episodes[index]
            episodeDisplayable.airDate shouldBe episode.airDate
            episodeDisplayable.characters shouldBe episode.characters
            episodeDisplayable.created shouldBe episode.created
            episodeDisplayable.episode shouldBe episode.episode
            episodeDisplayable.id shouldBe episode.id
            episodeDisplayable.name shouldBe episode.name
            episodeDisplayable.url shouldBe episode.url
        }
    }

    @Test
    fun `GIVEN use case result is failure WHEN episodes live data is observed THEN set idle state AND set error message in live data`() {
        //given
        val throwable = Throwable(message = "Ops... Something went wrong")
        val navigator = mockk<EpisodeNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true) {
            every { map(throwable) } returns throwable.message.toString()
        }
        val useCase = mockk<GetEpisodeUseCase>() {
            every { this@mockk(Unit, any(), any(), any()) } answers {
                lastArg<(Result<List<EpisodeDomain>>) -> Unit>()(Result.failure(throwable))
            }
        }
        val observer = mockk<Observer<String>>(relaxed = true)
        val viewModel = EpisodeViewModel(useCase, navigator, errorMapper)
        //when
        viewModel.message.observeForever(observer)
        viewModel.episodes.observeForTesting()
        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.IdleState
        verify { observer.onChanged(throwable.message.toString()) }
    }
}