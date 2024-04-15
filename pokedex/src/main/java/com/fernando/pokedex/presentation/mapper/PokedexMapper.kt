package com.fernando.pokedex.presentation.mapper

import com.fernando.pokedex.domain.model.Pokedex
import com.fernando.pokedex.presentation.model.PokedexDisplayable
import java.time.format.DateTimeFormatter

private const val TONNE = 1_000
private const val MILLION = 1_000_000

fun Pokedex.toPresentationModel() = PokedexDisplayable(
    id = id,
    name = name,
    costPerLaunchInMillions = costPerLaunch / MILLION,
    firstFlightDate = firstFlight.format(DateTimeFormatter.ISO_LOCAL_DATE),
    heightInMeters = height,
    weightInTonnes = weight / TONNE,
    wikiUrl = wikiUrl,
    imageUrl = imageUrl,
)
