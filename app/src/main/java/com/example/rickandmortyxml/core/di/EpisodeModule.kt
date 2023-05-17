package com.example.rickandmortyxml.core.di

import com.example.rickandmortyxml.core.api.RickAndMortyApi
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.example.rickandmortyxml.core.exception.ErrorWrapper
import com.example.rickandmortyxml.core.network.NetworkStateProvider
import com.example.rickandmortyxml.features.episodes.data.local.EpisodeDao
import com.example.rickandmortyxml.features.episodes.data.repository.EpisodeRepositoryImpl
import com.example.rickandmortyxml.features.episodes.domain.EpisodeRepository
import com.example.rickandmortyxml.features.episodes.domain.GetEpisodeUseCase
import com.example.rickandmortyxml.features.episodes.presentation.EpisodeAdapter
import com.example.rickandmortyxml.features.episodes.presentation.EpisodeFragment
import com.example.rickandmortyxml.features.episodes.presentation.EpisodeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodeModule = module {

    //data
    factory<EpisodeRepository> {
        EpisodeRepositoryImpl(
            get<RickAndMortyApi>(),
            get<EpisodeDao>(),
            get<NetworkStateProvider>(),
            get<ErrorWrapper>()
        )
    }

    //domain
    factory { GetEpisodeUseCase(get<EpisodeRepository>()) }

    //presentation
    viewModel { EpisodeViewModel(get<GetEpisodeUseCase>(), get<ErrorMapper>()) }
    factory { EpisodeAdapter() }
    factory { EpisodeFragment() }
}