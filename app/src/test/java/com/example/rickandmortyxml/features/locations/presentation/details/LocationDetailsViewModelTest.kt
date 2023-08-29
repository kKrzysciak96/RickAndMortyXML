package com.example.rickandmortyxml.features.locations.presentation.details

import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.characters.domain.GetMultipleCharactersUseCase
import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable
import com.example.rickandmortyxml.features.mockk.mock
import com.example.rickandmortyxml.features.utils.ViewModelTest
import com.example.rickandmortyxml.features.utils.getOrAwaitValue
import io.mockk.mockk
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class LocationDetailsViewModelTest : ViewModelTest() {

    @Test
    fun `WHEN location is passed from previous screen THEN show its details`() {
        //given
        val location = LocationDisplayable.mock()
        val errorMapper = mockk<ErrorMapper>()
        val useCase = mockk<GetMultipleCharactersUseCase>()
        val viewModel = LocationDetailsViewModel(useCase, errorMapper)
        //when
        viewModel.onLocationPassed(location)
        //then
        viewModel.location.getOrAwaitValue() shouldBe location
    }
}