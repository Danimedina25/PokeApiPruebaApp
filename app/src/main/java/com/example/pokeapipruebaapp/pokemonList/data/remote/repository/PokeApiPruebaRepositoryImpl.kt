package com.example.pokeapipruebaapp.pokemonList.data.remote.repository

import android.util.Log
import com.example.pokeapipruebaapp.pokemonList.data.database.PokeApiDatabase
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.toDomain
import com.example.pokeapipruebaapp.pokemonList.data.mappers.toDomain
import com.example.pokeapipruebaapp.pokemonList.data.mappers.toListPokemonsDomain
import com.example.pokeapipruebaapp.pokemonList.data.remote.services.PokeApi
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokeApiFavoritesModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.toDatabase
import com.example.pokeapipruebaapp.pokemonList.domain.model.toEntity
import com.example.pokeapipruebaapp.pokemonList.domain.repository.PokeApiPruebaRepository
import com.example.pokeapipruebaapp.utils.NetworkResult
import com.google.gson.Gson
import javax.inject.Inject

class PokeApiPruebaRepositoryImpl  @Inject constructor(
    private val pokeApi: PokeApi,
    private val pokeApiDatabase: PokeApiDatabase
): PokeApiPruebaRepository {

    override suspend fun getPokemons(offset: Int, limit:Int): PokemonListModel {
        val result =  pokeApiDatabase.getPokeApiDao().getListOfPokemons(offset, limit)
        return if(result == null){
            val response = pokeApi.getPokemons(offset, limit).toListPokemonsDomain()
            pokeApiDatabase.getPokeApiDao().insertListOfPokemons(response.toEntity())
            response
        }else{
            result.toDomain()
        }
    }

    override suspend fun getDataPokemon(id: Int): PokemonDataModel {
        val result =  pokeApiDatabase.getPokeApiDao().getDataPokemon(id)
        return if(result == null){
            val response = pokeApi.getDataPokemon(id).toDomain()
            pokeApiDatabase.getPokeApiDao().insertDataPokemon(response.toEntity(id))
            response
        }else{
            result.toDomain()
        }
    }

    override suspend fun getFormPokemon(id: Int): PokemonFormModel {
        pokeApi.getFormPokemon(id).toDomain()
        val result =  pokeApiDatabase.getPokeApiDao().getFormPokemon(id)
        return if(result == null){
            val response = pokeApi.getFormPokemon(id).toDomain()
            pokeApiDatabase.getPokeApiDao().insertFormPokemon(response.toEntity())
            response
        }else{
            result.toDomain()
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