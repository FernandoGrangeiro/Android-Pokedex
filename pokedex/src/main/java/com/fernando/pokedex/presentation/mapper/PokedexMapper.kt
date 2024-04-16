package com.fernando.pokedex.presentation.mapper

import com.fernando.pokedex.domain.model.Pokedex
import com.fernando.pokedex.presentation.model.PokedexDisplayable

private const val TONNE = 1_000
private const val MILLION = 1_000_000

fun Pokedex.toPresentationModel() = PokedexDisplayable(
    id = id,
    name = name,
    baseExperience = baseExperience,
    isDefault = isDefault,
    weight = weight,
    type = type,
    imageUrl = imageUrl
)
