package com.example.pokeapipruebaapp.pokemonList.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDataModel(
    val height: Int,
    val weight: Int,
): Parcelable