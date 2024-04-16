package com.fernando.pokedex.data.repository

import com.fernando.pokedex.data.local.dao.PokedexDao
import com.fernando.pokedex.data.mapper.toDomainModel
import com.fernando.pokedex.data.mapper.toEntityModel
import com.fernando.pokedex.data.remote.api.PokedexApi
import com.fernando.pokedex.domain.model.Pokedex
import com.fernando.pokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val PokedexApi: PokedexApi,
    private val PokedexDao: PokedexDao,
) : PokedexRepository {

    override fun getPokedex(): Flow<List<Pokedex>> {
        return PokedexDao
            .getPokedex()
            .map { PokedexCached ->
                PokedexCached.map { it.toDomainModel() }
            }
            .onEach { Pokedex ->
                if (Pokedex.isEmpty()) {
                    refreshPokedex()
                }
            }
    }

    override suspend fun refreshPokedex() {
        PokedexApi
            .getPokedex()
            .results.mapIndexed { index, pokemon ->
                pokemon.toDomainModel(index.toString()).toEntityModel()
            }
            .also {
                PokedexDao.savePokedex(it)
            }
    }
}
