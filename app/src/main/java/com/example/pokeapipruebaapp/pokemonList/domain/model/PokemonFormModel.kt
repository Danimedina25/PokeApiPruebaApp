package com.example.pokeapipruebaapp.pokemonList.domain.model

import android.os.Parcelable
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiDataEntity
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiFormEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonFormModel(
    val id: Int,
    val name: String,
    val sprites: SpritesModel,
    val types: PokemonTypesListModel
):Parcelable

fun PokemonFormModel.toEntity() = PokeApiFormEntity(
    id, name, sprites, types
)