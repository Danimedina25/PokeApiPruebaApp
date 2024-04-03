package com.example.pokeapipruebaapp.pokemonList.data.remote.repository

import android.util.Log
import com.example.pokeapipruebaapp.pokemonList.data.database.PokeApiDatabase
import com.example.pokeapipruebaapp.pokemonList.data.mappers.toDomain
import com.example.pokeapipruebaapp.pokemonList.data.mappers.toListPokemonsDomain
import com.example.pokeapipruebaapp.pokemonList.data.remote.services.PokeApi
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokeApiFavoritesModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.toDatabase
import com.example.pokeapipruebaapp.pokemonList.domain.repository.PokeApiPruebaRepository
import com.example.pokeapipruebaapp.utils.NetworkResult
import com.google.gson.Gson
import javax.inject.Inject

class PokeApiPruebaRepositoryImpl  @Inject constructor(
    private val pokeApi: PokeApi,
    private val pokeApiDatabase: PokeApiDatabase
): PokeApiPruebaRepository {

    override suspend fun getPokemons(offset: Int, limit:Int): NetworkResult<PokemonListModel> {
        return try {
            val result = pokeApi.getPokemons(offset, limit).toListPokemonsDomain()
            Log.d("resultRepository", Gson().toJson(result))
            NetworkResult.Success(
                data = result
            )

        } catch (e: Exception) {
            (NetworkResult.Error(
                message = "Unknown error",
            ))
        }
    }

    override suspend fun getDataPokemon(id: Int): NetworkResult<PokemonDataModel> {
        return try {
            val result = pokeApi.getDataPokemon(id).toDomain()
            NetworkResult.Success(
                data = result
            )

        } catch (e: Exception) {
            (NetworkResult.Error(
                message = "Unknown error",
            ))
        }
    }

    override suspend fun getFormPokemon(id: Int): NetworkResult<PokemonFormModel> {
        return try {
            val result = pokeApi.getFormPokemon(id).toDomain()
            NetworkResult.Success(
                data = result
            )

        } catch (e: Exception) {
            (NetworkResult.Error(
                message = "Unknown error",
            ))
        }
    }

    override suspend fun addToFavorites(pokeApiFavoritesModel: PokeApiFavoritesModel): Long{
       return pokeApiDatabase.getPokeApiDao().insertFavorite(pokeApiFavoritesModel.toDatabase())
    }

    override suspend fun deleteToFavorites(idPokemon: Int): Int {
        return pokeApiDatabase.getPokeApiDao().deleteFavorite(idPokemon)
    }

    override suspend fun checkIfIsFavorite(idPokemon: Int): Boolean {
        return pokeApiDatabase.getPokeApiDao().checkifFavoritePokemonExist(idPokemon)
    }
}