package com.example.rickandmortyxml.core.api

import com.example.rickandmortyxml.core.api.model.CharactersResponse
import com.example.rickandmortyxml.core.api.model.EpisodesResponse
import com.example.rickandmortyxml.core.api.model.LocationsResponse
import com.example.rickandmortyxml.features.characters.data.model.CharacterRemote
import com.example.rickandmortyxml.features.episodes.data.model.EpisodeRemote
import com.example.rickandmortyxml.features.locations.data.model.LocationRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("episode")
    suspend fun getAllEpisodes(): EpisodesResponse

    @GET("character")
    suspend fun getAllCharacters(): CharactersResponse

    @GET("location")
    suspend fun getAllLocations(): LocationsResponse

    @GET("episode/{listOfEpisodes}")
    suspend fun getMultipleEpisodes(@Path("listOfEpisodes") listOfEpisodes: String): List<EpisodeRemote>

    @GET("character/{listOfCharacters}")
    suspend fun getMultipleCharacters(@Path("listOfCharacters") listOfCharacters: String): List<CharacterRemote>

    @GET("location/{listOfLocations}")
    suspend fun getMultipleLocations(@Path("listOfLocations") listOfLocations: String): List<LocationRemote>
}