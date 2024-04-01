package com.example.pokeapipruebaapp.pokemonList.data.remote.services

import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.ApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApi {
    @GET("/api/v2/pokemon/")
    suspend fun getPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): ApiResponseDto

}
