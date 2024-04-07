package com.example.pokeapipruebaapp.pokemonList.domain.usecases

import android.util.Log
import com.example.pokeapipruebaapp.pokemonList.data.mappers.toListPokemonsDomain
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.repository.PokeApiPruebaRepository
import com.example.pokeapipruebaapp.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListOfPokemonsUseCase @Inject constructor(private val repository: PokeApiPruebaRepository) {

    suspend operator fun invoke(offset: Int, limit: Int): NetworkResult<PokemonListModel> {
        return try {
            val result = repository.getPokemons(offset, limit)
            Log.d("resultUsecase", Gson().toJson(result))
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