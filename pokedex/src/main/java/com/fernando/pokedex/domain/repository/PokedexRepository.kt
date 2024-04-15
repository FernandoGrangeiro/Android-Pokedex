package com.fernando.pokedex.domain.repository

import com.fernando.pokedex.domain.model.Pokedex
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {
    fun getPokedex(): Flow<List<Pokedex>>
    suspend fun refreshPokedex()
}
