package com.example.pokeapipruebaapp.pokemonList.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiEntity

@Dao
interface PokeApiDao {

/*    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfPokemons(pokeApiEntity: PokeApiEntity)

    @Query("SELECT * FROM poke_api_table")
    suspend fun getListOfPokemons(offset: Int, limit: Int): PokeApiEntity

    @Query("DELETE FROM poke_api_table")
    suspend fun deleteAllListOfPokemons()*/

}