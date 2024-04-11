package com.example.pokeapipruebaapp.pokemonList.domain.model

import com.example.pokeapipruebaapp.ItemModel
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiEntity

data class PokemonListModel(
    val listOfPokemons: List<PokemonModel>
)

fun PokemonListModel.toEntity():PokeApiEntity {
    val listOfPokemons = PokemonListModel(listOfPokemons)
    return PokeApiEntity(listOfPokemons)
}