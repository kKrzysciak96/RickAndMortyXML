package com.example.rickandmortyxml.features.characters.domain

import com.example.rickandmortyxml.core.base.BaseUseCase
import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain

class GetCharacterUseCase(private val characterRepository: CharacterRepository) :
    BaseUseCase<Unit, List<CharacterDomain>>() {
    override suspend fun action(params: Unit): List<CharacterDomain> {
        return characterRepository.getAllCharacters()
    }

}