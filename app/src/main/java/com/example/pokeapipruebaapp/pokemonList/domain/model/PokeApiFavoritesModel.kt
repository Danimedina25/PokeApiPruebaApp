package com.example.pokeapipruebaapp.pokemonList.domain.model

import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiFavoritesEntity

data class PokeApiFavoritesModel(
    val id_pokemon: Int,
    val name_pokemon: String
)

fun PokeApiFavoritesModel.toDatabase() = PokeApiFavoritesEntity(
    id_pokemon = id_pokemon, name_pokemon = name_pokemon
)
