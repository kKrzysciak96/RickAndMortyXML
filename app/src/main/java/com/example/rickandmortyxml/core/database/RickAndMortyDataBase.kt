package com.example.rickandmortyxml.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortyxml.features.characters.data.local.CharacterCached
import com.example.rickandmortyxml.features.characters.data.local.CharacterDao
import com.example.rickandmortyxml.features.episodes.data.local.EpisodeCached
import com.example.rickandmortyxml.features.episodes.data.local.EpisodeDao
import com.example.rickandmortyxml.features.locations.data.local.LocationCached
import com.example.rickandmortyxml.features.locations.data.local.LocationDao

@Database(
    entities = [EpisodeCached::class, CharacterCached::class, LocationCached::class],
    version = 1
)
@TypeConverters(ListConverter::class)
abstract class RickAndMortyDataBase : RoomDatabase() {
    abstract fun provideEpisodeDao(): EpisodeDao
    abstract fun provideCharacterDao(): CharacterDao
    abstract fun provideLocationDao(): LocationDao
}