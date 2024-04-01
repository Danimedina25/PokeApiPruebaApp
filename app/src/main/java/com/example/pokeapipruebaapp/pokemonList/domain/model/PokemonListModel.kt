package com.example.pokeapipruebaapp.pokemonList.domain.model

import com.example.pokeapipruebaapp.ItemModel

data class PokemonListModel(
    val listOfPokemons: List<PokemonModel>
)

fun PokemonListModel.pokemonListModeltoItemModel(): List<ItemModel> {
    val listItemModel = listOfPokemons.mapIndexed{ _, pokemon ->
       ItemModel(
            nombre = pokemon.name,
            url_image = pokemon.url
        )
    }
    return listItemModel
}