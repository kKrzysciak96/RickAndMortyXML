package com.example.rickandmortyxml.features.characters.domain

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import org.junit.jupiter.api.Test

internal class GetCharacterUseCaseTest {

    @Test
    fun `WHEN use case is invoked THEN getAllCharacters method of repository is called`() {
        //given
        val repository = mockk<CharacterRepository>(relaxed = true)
        val useCase = GetCharacterUseCase(repository)
        //when
        useCase.invoke(params = Unit, scope = GlobalScope)
        //then
        coVerify { repository.getAllCharacters() }
    }
}