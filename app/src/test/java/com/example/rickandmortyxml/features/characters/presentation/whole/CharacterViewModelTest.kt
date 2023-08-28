package com.example.rickandmortyxml.features.characters.presentation.whole

import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyxml.core.base.UiState
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.characters.domain.GetCharacterUseCase
import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain
import com.example.rickandmortyxml.features.characters.navigation.CharacterNavigator
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable
import com.example.rickandmortyxml.features.mockk.mock
import com.example.rickandmortyxml.features.utils.ViewModelTest
import com.example.rickandmortyxml.features.utils.getOrAwaitValue
import com.example.rickandmortyxml.features.utils.observeForTesting
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class CharacterViewModelTest : ViewModelTest() {

    @Test
    fun `WHEN character is clicked THEN open character details screen`() {
        //given
        val character = CharacterDisplayable.mock()
        val useCase = mockk<GetCharacterUseCase>(relaxed = true)
        val characterNavigator = mockk<CharacterNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = CharacterViewModel(useCase, characterNavigator, errorMapper)
        //when
        viewModel.onCharacterClick(character)
        //then
        verify { characterNavigator.openCharacterDetailsScreen(character) }
    }

    @Test
    fun `WHEN character live data is observed THEN set pending state`() {
        //given
        val useCase = mockk<GetCharacterUseCase>(relaxed = true)
        val characterNavigator = mockk<CharacterNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = CharacterViewModel(useCase, characterNavigator, errorMapper)
        //when
        viewModel.characters.observeForTesting()
        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.PendingState
    }

    @Test
    fun `WHEN character live data is observed THEN invoke use case to get characters`() {
        //given
        val useCase = mockk<GetCharacterUseCase>(relaxed = true)
        val characterNavigator = mockk<CharacterNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = CharacterViewModel(useCase, characterNavigator, errorMapper)
        //when
        viewModel.characters.observeForTesting()
        //then
        verify { useCase(Unit, viewModel.viewModelScope, any(), any()) }
    }

    @Test
    fun `GIVEN use case result is success WHEN character live data is observed THEN set idle state AND set result in live data`() {
        //given
        val characters =
            listOf(CharacterDomain.mock(), CharacterDomain.mock(), CharacterDomain.mock())
        val useCase = mockk<GetCharacterUseCase>() {
            every { this@mockk(Unit, any(), any(), any()) } answers {
                lastArg<(Result<List<CharacterDomain>>) -> Unit>()(Result.success(characters))
            }
        }
        val characterNavigator = mockk<CharacterNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = CharacterViewModel(useCase, characterNavigator, errorMapper)
        //when
        viewModel.characters.observeForTesting()
        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.IdleState
        viewModel.characters.getOrAwaitValue().forEachIndexed { index, characterDisplayable ->
            val character = characters[index]
            characterDisplayable.name shouldBe character.name
            characterDisplayable.status shouldBe character.status
            characterDisplayable.species shouldBe character.species
            characterDisplayable.type shouldBe character.type
            characterDisplayable.gender shouldBe character.gender
            characterDisplayable.origin.name shouldBe character.origin.name
            characterDisplayable.origin.url shouldBe character.origin.url
            characterDisplayable.location.name shouldBe character.location.name
            characterDisplayable.location.url shouldBe character.location.url
            characterDisplayable.image shouldBe character.image
            characterDisplayable.episode shouldBe character.episode
        }
    }

    @Test
    fun `GIVEN use case result is failure WHEN character live data is observed THEN set idle state AND set error message in live data`() {
        //given
        val throwable = Throwable("Ops... something went wrong")
        val getCharacterUseCase = mockk<GetCharacterUseCase>() {
            every { this@mockk(Unit, any(), any(), any()) } answers {
                lastArg<(Result<List<CharacterDomain>>) -> Unit>()(Result.failure(throwable))
            }
        }
        val characterNavigator = mockk<CharacterNavigator>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>() {
            every { map(throwable) } returns throwable.message.toString()
        }
        val viewModel = CharacterViewModel(getCharacterUseCase, characterNavigator, errorMapper)
        val observer = mockk<Observer<String>>(relaxed = true)
        //when
        viewModel.message.observeForever(observer)
        viewModel.characters.observeForTesting()
        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.IdleState
        verify { observer.onChanged(throwable.message.toString()) }
    }
}