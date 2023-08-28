package com.example.rickandmortyxml.features.episodes.domain

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import org.junit.jupiter.api.Test

internal class GetEpisodeUseCaseTest {

    @Test
    fun `WHEN use case is invoked THEN getEpisodes method of repository is called`() {
        //given
        val repository = mockk<EpisodeRepository>(relaxed = true)
        val useCase = GetEpisodeUseCase(repository)
        //when
        useCase.invoke(params = Unit, scope = GlobalScope)
        //then
        coVerify { repository.getEpisodes() }
    }
}