package com.example.pokeapipruebaapp.pokemonList.domain.model

import android.os.Parcelable
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiDataEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDataModel(
    val height: Int,
    val weight: Int,
): Parcelable

fun PokemonDataModel.toEntity(idPokemon: Int) = PokeApiDataEntity(
    idPokemon,
    height,
    weight
)