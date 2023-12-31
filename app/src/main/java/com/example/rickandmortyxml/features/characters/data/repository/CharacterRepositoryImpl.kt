package com.example.rickandmortyxml.features.characters.data.repository

import com.example.rickandmortyxml.core.api.RickAndMortyApi
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.exception.callOrThrow
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.features.characters.data.local.CharacterCached
import com.example.rickandmortyxml.features.characters.data.local.CharacterDao
import com.example.rickandmortyxml.features.characters.domain.CharacterRepository
import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain

class CharacterRepositoryImpl(
    private val rickAndMortyApi: RickAndMortyApi,
    private val characterDao: CharacterDao,
    private val networkStateProvider: NetworkStateProvider,
    private val errorWrapper: ErrorWrapper
) : CharacterRepository {
    override suspend fun getAllCharacters(): List<CharacterDomain> {
        return if (networkStateProvider.isNetworkAvailable()) {
            callOrThrow(errorWrapper) {
                getCharactersFromRemote().also { saveCharactersToLocal(it) }
            }
        } else {
            getCharactersFromLocal()
        }
    }

    override suspend fun getMultipleCharacters(listOfCharacters: String): List<CharacterDomain> {
        return if (networkStateProvider.isNetworkAvailable()) {
            callOrThrow(errorWrapper) {
                getMultipleCharactersFromRemote(listOfCharacters).also { saveCharactersToLocal(it) }
            }
        } else {
            getMultipleCharactersFromLocal(listOfCharacters)
        }
    }

    private suspend fun saveCharactersToLocal(characters: List<CharacterDomain>) {
        characters
            .map { CharacterCached(it) }
            .toTypedArray()
            .let { characterDao.saveAllCharacters(*it) }
    }

    private suspend fun getCharactersFromRemote(): List<CharacterDomain> {
        return rickAndMortyApi.getAllCharacters().results.map { it.toCharacterDomain() }
    }

    private suspend fun getMultipleCharactersFromRemote(listOfCharacters: String): List<CharacterDomain> {
        return rickAndMortyApi.getMultipleCharacters(listOfCharacters)
            .map { it.toCharacterDomain() }
    }

    private suspend fun getMultipleCharactersFromLocal(listOfCharacters: String): List<CharacterDomain> {
        val allCharactersInDataBase = characterDao.getAllCharacters()
        val characterIDList = listOfCharacters.split(",").map { it.toInt() }

        val requiredCharacters = allCharactersInDataBase.filter { characterCached ->
            characterCached.id in characterIDList
        }
        return requiredCharacters.map { it.toCharacterDomain() }
    }

    private suspend fun getCharactersFromLocal(): List<CharacterDomain> {
        return characterDao.getAllCharacters().map { it.toCharacterDomain() }
    }
}