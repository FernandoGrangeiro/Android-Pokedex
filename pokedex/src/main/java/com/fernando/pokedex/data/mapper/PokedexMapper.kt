package com.fernando.pokedex.data.mapper

import com.fernando.pokedex.data.local.model.PokedexCached
import com.fernando.pokedex.data.remote.model.PokedexResponse
import com.fernando.pokedex.domain.model.Pokedex
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun PokedexResponse.toDomainModel() = Pokedex(
    id = id,
    name = name,
    costPerLaunch = costPerLaunch,
    firstFlight = LocalDate.parse(firstFlightDate),
    height = height.meters.toInt(),
    weight = weight.kg,
    wikiUrl = wikiUrl,
    imageUrl = imageUrls.random(),
)

fun PokedexCached.toDomainModel() = Pokedex(
    id = id,
    name = name,
    costPerLaunch = costPerLaunch,
    firstFlight = LocalDate.parse(firstFlightDate),
    height = height,
    weight = weight,
    wikiUrl = wikiUrl,
    imageUrl = imageUrl,
)

fun Pokedex.toEntityModel() = PokedexCached(
    id = id,
    name = name,
    costPerLaunch = costPerLaunch,
    firstFlightDate = firstFlight.format(DateTimeFormatter.ISO_LOCAL_DATE),
    height = height,
    weight = weight,
    wikiUrl = wikiUrl,
    imageUrl = imageUrl,
)
