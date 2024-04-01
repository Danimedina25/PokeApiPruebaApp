package com.example.pokeapipruebaapp.pokemonList.data.mappers

import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.ApiResponseDto
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonModel


fun ApiResponseDto.toListPokemonsDomain(): PokemonListModel {
    val listOfPokemons = results.mapIndexed { _, pokemon ->
        PokemonModel(
            name = pokemon.name,
            url = pokemon.url
        )
    }
    val pokemons = PokemonListModel(listOfPokemons = listOfPokemons)
    return pokemons
}