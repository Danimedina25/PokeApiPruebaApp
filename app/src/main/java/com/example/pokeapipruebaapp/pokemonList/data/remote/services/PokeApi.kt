package com.example.pokeapipruebaapp.pokemonList.data.remote.services

import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.dataPokemon.ApiResponseDataDto
import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.formPokemon.ApiResponseFormDto
import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.listOfPokemons.ApiResponseDto
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("/api/v2/pokemon/")
    suspend fun getPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): ApiResponseDto

    @GET("/api/v2/pokemon/{id}")
    suspend fun getDataPokemon(@Path("id") id: Int): ApiResponseDataDto

    @GET("/api/v2/pokemon-form/{id}")
    suspend fun getFormPokemon(@Path("id") id: Int): ApiResponseFormDto

}
