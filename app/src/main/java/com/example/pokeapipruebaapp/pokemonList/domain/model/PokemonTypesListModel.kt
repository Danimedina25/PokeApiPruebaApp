package com.example.pokeapipruebaapp.pokemonList.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonTypesListModel(
    val listOfTypes: List<TypesModel>
): Parcelable

