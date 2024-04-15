package com.fernando.pokedex.domain.usecase

import com.fernando.pokedex.domain.repository.PokedexRepository
import eu.krzdabrowski.starter.core.utils.resultOf

fun interface RefreshPokedexUseCase : suspend () -> Result<Unit>

suspend fun refreshPokedex(
    PokedexRepository: PokedexRepository,
): Result<Unit> = resultOf {
    PokedexRepository.refreshPokedex()
}
