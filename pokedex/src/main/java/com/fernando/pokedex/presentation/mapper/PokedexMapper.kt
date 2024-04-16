package com.fernando.pokedex.presentation.mapper

import com.fernando.pokedex.domain.model.Pokedex
import com.fernando.pokedex.presentation.model.PokedexDisplayable
fun Pokedex.toPresentationModel() = PokedexDisplayable(
    id = id,
    name = name,
    baseExperience = baseExperience,
    isDefault = isDefault,
    weight = weight,
    type = type,
    imageUrl = imageUrl,
)
