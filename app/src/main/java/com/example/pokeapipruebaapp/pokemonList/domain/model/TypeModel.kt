package com.example.pokeapipruebaapp.pokemonList.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TypeModel(
    val url: String,
    val name: String,
): Parcelable