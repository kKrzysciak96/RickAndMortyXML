package com.example.rickandmortyxml.core.di

import androidx.room.Room
import com.example.rickandmortyxml.core.database.RickAndMortyDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            RickAndMortyDataBase::class.java,
            "rickAndMortyDatabase"
        ).fallbackToDestructiveMigration().build()
    }
    single { get<RickAndMortyDataBase>().provideEpisodeDao() }
    single { get<RickAndMortyDataBase>().provideCharacterDao() }
    single { get<RickAndMortyDataBase>().provideLocationDao() }
}