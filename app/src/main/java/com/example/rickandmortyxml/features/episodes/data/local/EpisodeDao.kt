package com.example.rickandmortyxml.features.episodes.data.local

import androidx.room.*


@Dao
interface EpisodeDao {

    @Query("SELECT * FROM EpisodeCached")
    suspend fun getAllEpisodes(): List<EpisodeCached>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllEpisodes(vararg episodes: EpisodeCached)

}