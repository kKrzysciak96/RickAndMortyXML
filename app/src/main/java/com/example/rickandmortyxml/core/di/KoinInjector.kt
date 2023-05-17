package com.example.rickandmortyxml.core.di

import org.koin.core.module.Module

val koinInjector: List<Module> = listOf(
    appModule,
    databaseModule,
    characterModule,
    locationModule,
    episodeModule,
    networkModule
)