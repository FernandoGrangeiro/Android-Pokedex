package com.fernando.pokedex.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokedexCached(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "base_experience")
    val baseExperience: Int,

    @ColumnInfo(name = "is_default")
    val isDefault: Boolean,

    @ColumnInfo(name = "typeA")
    val type: String,

    @ColumnInfo(name = "weight")
    val weight: Double,

    @ColumnInfo(name = "image_url")
    val imageUrl: String
)
