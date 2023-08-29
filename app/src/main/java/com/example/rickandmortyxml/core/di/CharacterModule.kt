package com.example.rickandmortyxml.core.di

import com.example.rickandmortyxml.core.api.RickAndMortyApi
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.navigation.FragmentNavigator
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.features.characters.data.local.CharacterDao
import com.example.rickandmortyxml.features.characters.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyxml.features.characters.domain.CharacterRepository
import com.example.rickandmortyxml.features.characters.domain.GetCharacterUseCase
import com.example.rickandmortyxml.features.characters.navigation.CharacterDetailsNavigator
import com.example.rickandmortyxml.features.characters.navigation.CharacterDetailsNavigatorImpl
import com.example.rickandmortyxml.features.characters.navigation.CharacterNavigator
import com.example.rickandmortyxml.features.characters.navigation.CharacterNavigatorImpl
import com.example.rickandmortyxml.features.characters.presentation.details.CharacterDetailsFragment
import com.example.rickandmortyxml.features.characters.presentation.details.CharacterDetailsViewModel
import com.example.rickandmortyxml.features.characters.presentation.whole.CharacterAdapter
import com.example.rickandmortyxml.features.characters.presentation.whole.CharacterFragment
import com.example.rickandmortyxml.features.characters.presentation.whole.CharacterViewModel
import com.example.rickandmortyxml.features.characters.presentation.zoom.ZoomedPhotoDialog
import com.example.rickandmortyxml.features.characters.presentation.zoom.ZoomedPhotoDialogViewModel
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
    factory<CharacterNavigator> { CharacterNavigatorImpl(get<FragmentNavigator>()) }
    viewModel {
        CharacterViewModel(
            get<GetCharacterUseCase>(),
            get<CharacterNavigator>(),
            get<ErrorMapper>()
        )
    }
    factory { CharacterAdapter() }
    factory { CharacterFragment() }
    factory<CharacterDetailsNavigator> { CharacterDetailsNavigatorImpl(get<FragmentNavigator>()) }
    viewModel { CharacterDetailsViewModel(get<CharacterDetailsNavigator>(), get<ErrorMapper>()) }
    factory { CharacterDetailsFragment() }

    viewModel { ZoomedPhotoDialogViewModel() }
    factory { ZoomedPhotoDialog() }
}