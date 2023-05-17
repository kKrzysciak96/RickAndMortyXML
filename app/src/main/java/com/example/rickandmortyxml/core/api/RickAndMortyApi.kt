package com.example.rickandmortyxml.core.api

import com.example.rickandmortyxml.core.api.model.CharactersResponse
import com.example.rickandmortyxml.core.api.model.EpisodesResponse
import com.example.rickandmortyxml.core.api.model.LocationsResponse
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("episode")
    suspend fun getAllEpisodes(): EpisodesResponse

    @GET("character")
    suspend fun getAllCharacters(): CharactersResponse

    @GET("location")
    suspend fun getAllLocations(): LocationsResponse
}