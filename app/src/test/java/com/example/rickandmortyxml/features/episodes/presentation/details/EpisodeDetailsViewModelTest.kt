package com.example.rickandmortyxml.features.episodes.presentation.details

import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.characters.domain.GetMultipleCharactersUseCase
import com.example.rickandmortyxml.features.episodes.presentation.model.EpisodeDisplayable
import com.example.rickandmortyxml.features.mockk.mock
import com.example.rickandmortyxml.features.utils.ViewModelTest
import com.example.rickandmortyxml.features.utils.getOrAwaitValue
import io.mockk.mockk
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class EpisodeDetailsViewModelTest : ViewModelTest() {

    @Test
    fun `WHEN episode is passed from previous screen THEN show its details`() {
        //given
        val episode = EpisodeDisplayable.mock()
        val errorMapper = mockk<ErrorMapper>()
        val useCase = mockk<GetMultipleCharactersUseCase>()
        val viewModel = EpisodeDetailsViewModel(useCase, errorMapper)
        //when
        viewModel.onEpisodePassed(episode)
        //then
        viewModel.episode.getOrAwaitValue() shouldBe episode
    }
}