package com.example.pokeapipruebaapp.pokemonList.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TypesModel(
    val slot: Int,
    val type: TypeModel
): Parcelable