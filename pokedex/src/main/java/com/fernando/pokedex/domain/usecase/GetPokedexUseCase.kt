package com.fernando.pokedex.domain.usecase

import com.fernando.pokedex.domain.model.Pokedex
import com.fernando.pokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException

private const val RETRY_TIME_IN_MILLIS = 15_000L

fun interface GetPokedexUseCase : () -> Flow<Result<List<Pokedex>>>

fun getPokedex(
    pokedexRepository: PokedexRepository,
): Flow<Result<List<Pokedex>>> = pokedexRepository
    .getPokedex()
    .map { Result.success(it) }
    .retryWhen { cause, _ ->
        if (cause is IOException) {
            emit(Result.failure(cause))
            delay(RETRY_TIME_IN_MILLIS)
            true
        } else {
            false
        }
    }
    .catch { // for other than IOException but it will stop collecting Flow
        emit(Result.failure(it)) // also catch does re-throw CancellationException
    }
