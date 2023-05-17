package com.example.rickandmortyxml.core.di

import com.example.rickandmortyxml.core.api.RickAndMortyApi
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.features.characters.data.local.CharacterDao
import com.example.rickandmortyxml.features.characters.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyxml.features.characters.domain.CharacterRepository
import com.example.rickandmortyxml.features.characters.domain.GetCharacterUseCase
import com.example.rickandmortyxml.features.characters.presentation.CharacterAdapter
import com.example.rickandmortyxml.features.characters.presentation.CharacterFragment
import com.example.rickandmortyxml.features.characters.presentation.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {

    //data
    factory<CharacterRepository> {
        CharacterRepositoryImpl(
            get<RickAndMortyApi>(),
            get<CharacterDao>(),
            get<NetworkStateProvider>(),
            get<ErrorWrapper>()
        )
    }

    //domain
    factory { GetCharacterUseCase(get<CharacterRepository>()) }

    //presentation
    viewModel { CharacterViewModel(get<GetCharacterUseCase>(), get<ErrorMapper>()) }
    factory { CharacterAdapter() }
    factory { CharacterFragment() }
}