package com.example.rickandmortyxml.features.locations.presentation.whole

import androidx.lifecycle.Observer
import com.example.rickandmortyxml.core.base.UiState
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.locations.domain.GetLocationUseCase
import com.example.rickandmortyxml.features.locations.domain.model.LocationDomain
import com.example.rickandmortyxml.features.locations.navigation.LocationNavigator
import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable
import com.example.rickandmortyxml.features.mockk.mock
import com.example.rickandmortyxml.features.utils.ViewModelTest
import com.example.rickandmortyxml.features.utils.getOrAwaitValue
import com.example.rickandmortyxml.features.utils.observeForTesting
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class LocationViewModelTest : ViewModelTest() {

    @Test
    fun `WHEN location is clicked THEN open location details screen`() {
        //given
        val location = LocationDisplayable.mock()
        val getLocationUseCase = mockk<GetLocationUseCase>(relaxed = true)
        val locationNavigator = mockk<LocationNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = LocationViewModel(getLocationUseCase, locationNavigator, errorMapper)
        //when
        viewModel.onLocationClick(location)
        //then
        verify { locationNavigator.openLocationDetailsScreen(location) }
    }

    @Test
    fun `WHEN location live data is observed THEN set pending state`() {
        //given
        val getLocationUseCase = mockk<GetLocationUseCase>(relaxed = true)
        val locationNavigator = mockk<LocationNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = LocationViewModel(getLocationUseCase, locationNavigator, errorMapper)
        //when
        viewModel.location.observeForTesting()
        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.PendingState
    }

    @Test
    fun `WHEN location live data is observed THEN invoke use case to get locations`() {
        //given
        val getLocationUseCase = mockk<GetLocationUseCase>(relaxed = true)
        val locationNavigator = mockk<LocationNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = LocationViewModel(getLocationUseCase, locationNavigator, errorMapper)
        //when
        viewModel.location.observeForTesting()
        //then
        verify { getLocationUseCase(Unit, any(), any(), any()) }
    }

    @Test
    fun `GIVEN use case result is success WHEN location live data is observed THEN set idle state AND set result in live data`() {
        //given
        val locations = listOf(
            LocationDomain.mock(),
            LocationDomain.mock(),
            LocationDomain.mock()
        )
        val getLocationUseCase = mockk<GetLocationUseCase> {
            every { this@mockk(Unit, any(), any(), any()) } answers {
                lastArg<(Result<List<LocationDomain>>) -> Unit>()(Result.success(locations))
            }
        }
        val locationNavigator = mockk<LocationNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = LocationViewModel(getLocationUseCase, locationNavigator, errorMapper)
        //when
        viewModel.location.observeForTesting()
        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.IdleState
        viewModel.location.getOrAwaitValue().forEachIndexed { index, locationDisplayable ->
            val location = locations[index]
            locationDisplayable.created shouldBe location.created
            locationDisplayable.dimension shouldBe location.dimension
            locationDisplayable.id shouldBe location.id
            locationDisplayable.name shouldBe location.name
            locationDisplayable.residents shouldBe location.residents
            locationDisplayable.type shouldBe location.type
            locationDisplayable.url shouldBe location.url
        }
    }

    @Test
    fun `GIVEN use case result is failure WHEN location live data is observed THEN set idle state AND set error message in live data`() {
        //given
        val throwable = Throwable("Ops... something went wrong")
        val getLocationUseCase = mockk<GetLocationUseCase> {
            every { this@mockk(Unit, any(), any(), any()) } answers {
                lastArg<(Result<List<LocationDomain>>) -> Unit>()(Result.failure(throwable))
            }
        }
        val locationNavigator = mockk<LocationNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper> {
            every { map(throwable) } returns throwable.message.toString()
        }
        val viewModel = LocationViewModel(getLocationUseCase, locationNavigator, errorMapper)
        val observer = mockk<Observer<String>>(relaxed = true)
        //when
        viewModel.message.observeForever(observer)
        viewModel.location.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.IdleState
        verify { observer.onChanged(throwable.message.toString()) }
    }
}