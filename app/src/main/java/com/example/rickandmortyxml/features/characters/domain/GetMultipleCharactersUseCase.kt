package com.example.rickandmortyxml.features.characters.domain

import com.example.rickandmortyxml.core.base.BaseUseCase
import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain

class GetMultipleCharactersUseCase(private val characterRepository: CharacterRepository) :
    BaseUseCase<String, List<CharacterDomain>>() {
    override suspend fun action(params: String): List<CharacterDomain> {
        return characterRepository.getMultipleCharacters(params)
    }
}