package com.example.pokeapipruebaapp.pokemonList.domain.repository


import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PokeApiPruebaRepository {
    suspend fun getPokemons(offset: Int, limit: Int): NetworkResult<PokemonListModel>
    suspend fun getDataPokemon(id:Int): NetworkResult<PokemonListModel>
}

