package com.example.rickandmortyxml.features.characters.presentation.details

import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.features.characters.navigation.CharacterDetailsNavigator
import com.example.rickandmortyxml.features.characters.navigation.CharacterDetailsNavigatorImpl
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable
import com.example.rickandmortyxml.features.mockk.mock
import com.example.rickandmortyxml.features.utils.ViewModelTest
import com.example.rickandmortyxml.features.utils.getOrAwaitValue
import io.mockk.mockk
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class CharacterDetailsViewModelTest : ViewModelTest() {

    @Test
    fun `WHEN character is passed from previous screen THEN show its details`() {
        //given
        val character = CharacterDisplayable.mock()
        val characterDetailsNavigator: CharacterDetailsNavigator =
            mockk<CharacterDetailsNavigatorImpl>()
        val errorMapper = mockk<ErrorMapper>()
        val viewModel = CharacterDetailsViewModel(characterDetailsNavigator, errorMapper)
        //when
        viewModel.onCharacterPassed(character)
        //then
        viewModel.character.getOrAwaitValue() shouldBe character
    }
}