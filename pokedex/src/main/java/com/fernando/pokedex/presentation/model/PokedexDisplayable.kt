package com.fernando.pokedex.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokedexDisplayable(
    val id: String,
    val name: String,
    val baseExperience: Int,
    val weight: Double,
    val isDefault: Boolean,
    val type: String,
    val imageUrl: String,
) : Parcelable
