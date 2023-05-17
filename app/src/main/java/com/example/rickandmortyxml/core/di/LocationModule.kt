package com.example.rickandmortyxml.core.di

import com.example.rickandmortyxml.core.api.RickAndMortyApi
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.features.locations.data.local.LocationDao
import com.example.rickandmortyxml.features.locations.data.repository.LocationRepositoryImpl
import com.example.rickandmortyxml.features.locations.domain.GetLocationUseCase
import com.example.rickandmortyxml.features.locations.domain.LocationRepository
import com.example.rickandmortyxml.features.locations.presentation.LocationAdapter
import com.example.rickandmortyxml.features.locations.presentation.LocationFragment
import com.example.rickandmortyxml.features.locations.presentation.LocationViewModel
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
    viewModel { LocationViewModel(get<GetLocationUseCase>(), get<ErrorMapper>()) }
    factory { LocationAdapter() }
    factory { LocationFragment() }
}