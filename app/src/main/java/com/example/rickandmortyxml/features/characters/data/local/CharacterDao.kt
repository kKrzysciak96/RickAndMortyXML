package com.example.rickandmortyxml.features.characters.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterCached")
    suspend fun getAllCharacters(): List<CharacterCached>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCharacters(vararg character: CharacterCached)
}