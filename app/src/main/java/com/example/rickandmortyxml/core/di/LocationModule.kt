package com.example.rickandmortyxml.core.di

import com.example.rickandmortyxml.core.api.RickAndMortyApi
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.navigation.FragmentNavigator
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.features.locations.data.local.LocationDao
import com.example.rickandmortyxml.features.locations.data.repository.LocationRepositoryImpl
import com.example.rickandmortyxml.features.locations.domain.GetLocationUseCase
import com.example.rickandmortyxml.features.locations.domain.LocationRepository
import com.example.rickandmortyxml.features.locations.navigation.LocationNavigator
import com.example.rickandmortyxml.features.locations.navigation.LocationNavigatorImpl
import com.example.rickandmortyxml.features.locations.presentation.details.LocationDetailsFragment
import com.example.rickandmortyxml.features.locations.presentation.details.LocationDetailsViewModel
import com.example.rickandmortyxml.features.locations.presentation.whole.LocationAdapter
import com.example.rickandmortyxml.features.locations.presentation.whole.LocationFragment
import com.example.rickandmortyxml.features.locations.presentation.whole.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationModule = module {

    //data
    factory<LocationRepository> {
        LocationRepositoryImpl(
            get<RickAndMortyApi>(),
            get<LocationDao>(),
            get<NetworkStateProvider>(),
            get<ErrorWrapper>()
        )
    }

    //domain
    factory { GetLocationUseCase(get<LocationRepository>()) }

    //presentation
    factory<LocationNavigator> { LocationNavigatorImpl(get<FragmentNavigator>()) }
    viewModel {
        LocationViewModel(
            get<GetLocationUseCase>(),
            get<LocationNavigator>(),
            get<ErrorMapper>()
        )
    }
    factory { LocationAdapter() }
    factory { LocationFragment() }
    viewModel { LocationDetailsViewModel(get<ErrorMapper>()) }
    factory { LocationDetailsFragment() }
}