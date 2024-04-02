package com.example.pokeapipruebaapp.pokemonList.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonFormModel(
    val id: Int,
    val name: String,
    val sprites: SpritesModel,
    val types: List<TypesModel>,
):Parcelable