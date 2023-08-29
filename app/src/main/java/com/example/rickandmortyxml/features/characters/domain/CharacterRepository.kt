package com.example.rickandmortyxml.features.characters.domain

import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain

interface CharacterRepository {
    suspend fun getAllCharacters(): List<CharacterDomain>
    suspend fun getMultipleCharacters(listOfCharacters: String): List<CharacterDomain>
}