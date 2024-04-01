package com.example.pokeapipruebaapp.pokemonList.data.mappers

import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.listOfPokemons.ApiResponseDto
import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.listOfPokemons.getIdPokemon
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonModel


fun ApiResponseDto.toListPokemonsDomain(): PokemonListModel {
    val listOfPokemons = results.mapIndexed { _, pokemon ->
        PokemonModel(
            id = pokemon.getIdPokemon(),
            name = pokemon.name,
            url = pokemon.url,
            //data = PokemonDataModel(5,8, PokemonFormModel(0, "", SpritesModel("", ""), emptyList()))
        )
    }
    return PokemonListModel(listOfPokemons = listOfPokemons)
}