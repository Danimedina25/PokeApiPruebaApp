package com.example.pokeapipruebaapp.pokemonList.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiDataEntity
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiEntity
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiFavoritesEntity
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiFormEntity

@Dao
interface PokeApiDao {

    //listOfPokemons
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfPokemons(pokeApiEntity: PokeApiEntity)

    @Query("SELECT * FROM poke_api_table WHERE id BETWEEN :limit and :offset")
    suspend fun getListOfPokemons(limit: Int, offset: Int): PokeApiEntity

    @Query("DELETE FROM poke_api_table")
    suspend fun deleteAllListOfPokemons()

    //favoritos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(pokeApiFavoritesEntity: PokeApiFavoritesEntity): Long
    @Query("SELECT * FROM poke_api_favorites_table")
    suspend fun getAllFavorites(): PokeApiFavoritesEntity
    @Query("DELETE FROM poke_api_favorites_table")
    suspend fun deleteAllFavorites()
    @Query("DELETE FROM poke_api_favorites_table WHERE id_pokemon=:idPokemon")
    suspend fun deleteFavorite(idPokemon: Int): Int
    @Query("SELECT EXISTS(SELECT * FROM poke_api_favorites_table WHERE id_pokemon=:idPokemon)")
    fun checkifFavoritePokemonExist(idPokemon: Int) : Boolean
    @Query("SELECT * FROM poke_api_favorites_table WHERE id_pokemon=:idPokemon")
    suspend fun getFavorite(idPokemon: Int):PokeApiFavoritesEntity

    //data pokemon
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertDataPokemon(pokeApiDataEntity: PokeApiDataEntity)
   @Query("SELECT * FROM poke_api_data_table WHERE idPokemon=:idPokemon")
   suspend fun getDataPokemon(idPokemon: Int):PokeApiDataEntity
   @Query("DELETE FROM poke_api_data_table")
   suspend fun deleteDataPokemons()

    //form pokemon
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFormPokemon(pokeApiFormEntity: PokeApiFormEntity)
    @Query("SELECT * FROM poke_api_form_table WHERE idPokemon=:idPokemon")
    suspend fun getFormPokemon(idPokemon: Int): PokeApiFormEntity
    @Query("DELETE FROM poke_api_form_table")
    suspend fun deleteFormPokemons()
}