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
    private val pokedexApi: PokedexApi,
    private val pokedexDao: PokedexDao,
) : PokedexRepository {

    override fun getPokedex(): Flow<List<Pokedex>> {
        return pokedexDao
            .getPokedex()
            .map { pokedexCached ->
                pokedexCached.map { it.toDomainModel() }
            }
            .onEach { pokedex ->
                if (pokedex.isEmpty()) {
                    refreshPokedex()
                }
            }
    }

    override suspend fun refreshPokedex() {
        pokedexApi
            .getPokedex()
            .results.mapIndexed { index, pokemon ->
                pokemon.toDomainModel(index.toString()).toEntityModel()
            }
            .also {
                pokedexDao.savePokedex(it)
            }
    }
}
