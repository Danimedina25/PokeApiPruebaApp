package com.example.pokeapipruebaapp.pokemonList.domain.usecases

import android.util.Log
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.repository.PokeApiPruebaRepository
import com.example.pokeapipruebaapp.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataPokemonUseCase @Inject constructor(private val repository: PokeApiPruebaRepository) {

    suspend operator fun invoke(id: Int): NetworkResult<PokemonDataModel> {
        return try {
            val result = repository.getDataPokemon(id)
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