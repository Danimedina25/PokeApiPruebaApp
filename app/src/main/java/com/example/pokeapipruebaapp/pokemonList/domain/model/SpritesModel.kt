package com.example.pokeapipruebaapp.pokemonList.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpritesModel(
    val back_default: String,
    val front_default: String,
): Parcelable