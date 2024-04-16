package com.fernando.pokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.fernando.pokedex.data.local.model.PokedexCached
import kotlinx.coroutines.flow.Flow

@Dao
interface PokedexDao {

    @Query("SELECT * FROM PokedexCached")
    fun getPokedex(): Flow<List<PokedexCached>>

    @Upsert
    suspend fun savePokedex(pokedex: List<PokedexCached>) }
