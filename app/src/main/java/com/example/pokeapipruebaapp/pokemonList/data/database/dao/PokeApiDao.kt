package com.example.pokeapipruebaapp.pokemonList.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiEntity
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiFavoritesEntity

@Dao
interface PokeApiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfPokemons(pokeApiEntity: PokeApiEntity)

    @Query("SELECT * FROM poke_api_table")
    suspend fun getListOfPokemons(): PokeApiEntity

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
    fun isFavoriteIsExist(idPokemon: Int) : Boolean
    @Query("SELECT * FROM poke_api_favorites_table WHERE id_pokemon=:idPokemon")
    suspend fun getFavorite(idPokemon: Int):PokeApiFavoritesEntity
}