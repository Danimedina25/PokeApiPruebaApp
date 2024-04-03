package com.example.pokeapipruebaapp.pokemonList.domain.usecases

import com.example.pokeapipruebaapp.pokemonList.domain.repository.PokeApiPruebaRepository
import javax.inject.Inject

class CheckIfIsFavoriteUseCase @Inject constructor(private val repository: PokeApiPruebaRepository) {

    suspend operator fun invoke(idPokemon:Int): Boolean {
        return repository.checkIfIsFavorite(idPokemon)
    }
}