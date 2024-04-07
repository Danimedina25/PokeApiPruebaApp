package com.example.pokeapipruebaapp.pokemonList.domain.repository


import com.example.pokeapipruebaapp.pokemonList.domain.model.PokeApiFavoritesModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.utils.NetworkResult

interface PokeApiPruebaRepository {
    suspend fun getPokemons(offset: Int, limit: Int): PokemonListModel
    suspend fun getDataPokemon(id:Int): PokemonDataModel
    suspend fun getFormPokemon(id: Int): PokemonFormModel
    suspend fun addToFavorites(pokeApiFavoritesModel: PokeApiFavoritesModel): Long
    suspend fun deleteToFavorites(idPokemon: Int): Int
    suspend fun checkIfIsFavorite(idPokemon: Int): Boolean
}

