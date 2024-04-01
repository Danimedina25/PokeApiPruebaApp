package com.example.pokeapipruebaapp.pokemonList.data.remote.repository

import com.example.pokeapipruebaapp.pokemonList.data.mappers.toListPokemonsDomain
import com.example.pokeapipruebaapp.pokemonList.data.remote.services.PokeApi
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.repository.PokeApiPruebaRepository
import com.example.pokeapipruebaapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokeApiPruebaRepositoryImpl  @Inject constructor(
    private val pokeApi: PokeApi
): PokeApiPruebaRepository {

    override suspend fun getPokemons(offset: Int, limit:Int): NetworkResult<PokemonListModel> {
        return try {
            val result = pokeApi.getPokemons(offset, limit).toListPokemonsDomain()
            NetworkResult.Success(
                data = result
            )

        } catch (e: Exception) {
            (NetworkResult.Error(
                message = "Unknown error",
            ))
        }
        }
    }