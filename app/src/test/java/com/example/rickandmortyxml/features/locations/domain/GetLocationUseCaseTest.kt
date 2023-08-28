package com.example.rickandmortyxml.features.locations.domain

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import org.junit.jupiter.api.Test

internal class GetLocationUseCaseTest {

    @Test
    fun `WHEN useCase is invoked THEN getAllLocations method of repository is called`() {
        //given
        val repository = mockk<LocationRepository>(relaxed = true)
        val useCase = GetLocationUseCase(repository)
        //when
        useCase.invoke(params = Unit, scope = GlobalScope)
        //then
        coVerify { repository.getAllLocations() }
    }
}