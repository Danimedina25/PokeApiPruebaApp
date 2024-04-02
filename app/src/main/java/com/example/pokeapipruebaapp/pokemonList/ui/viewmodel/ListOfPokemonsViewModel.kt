package com.example.pokeapipruebaapp.pokemonList.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapipruebaapp.ItemModel
import com.example.pokeapipruebaapp.pokemonList.data.database.dao.PokeApiDao
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokeApiFavoritesModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.toDatabase
import com.example.pokeapipruebaapp.pokemonList.domain.usecases.AddToFavoritesUseCase
import com.example.pokeapipruebaapp.pokemonList.domain.usecases.DeleteFavoriteUseCase
import com.example.pokeapipruebaapp.pokemonList.domain.usecases.GetDataPokemonUseCase
import com.example.pokeapipruebaapp.pokemonList.domain.usecases.GetFormPokemonUseCase
import com.example.pokeapipruebaapp.pokemonList.domain.usecases.GetListOfPokemonsUseCase
import com.example.pokeapipruebaapp.utils.NetworkResult
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfPokemonsViewModel @Inject constructor(
    private val getListOfPokemonsUseCase: GetListOfPokemonsUseCase,
    private val getDataPokemonUseCase: GetDataPokemonUseCase,
    private val getFormPokemonUseCase: GetFormPokemonUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _getPokemonsResult = MutableLiveData<PokemonListModel>()
    val getPokemonsResult: LiveData<PokemonListModel>
        get() = _getPokemonsResult

    private val _listOfItemModel = MutableLiveData<List<ItemModel>>(emptyList())
    val listOfItemModel: LiveData<List<ItemModel>>
        get() = _listOfItemModel

    private val _getDataPokemonResult = MutableLiveData<PokemonDataModel>()
    val getDataPokemonResult: LiveData<PokemonDataModel>
        get() = _getDataPokemonResult

    private val _getFormPokemonResult = MutableLiveData<PokemonFormModel>()
    val getFormPokemonResult: LiveData<PokemonFormModel>
        get() = _getFormPokemonResult

    private val _getPokemonsErrorMessage = MutableLiveData<String>()
    val getPokemonsErrorMessage: LiveData<String>
        get() = _getPokemonsErrorMessage

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _addToFavoritesResult = MutableLiveData<Long>(0)
    val addToFavoritesResult: LiveData<Long>
        get() = _addToFavoritesResult

    private val _deleteFavoriteResult = MutableLiveData<Int>(0)
    val deleteFavoriteResult: LiveData<Int>
        get() = _deleteFavoriteResult

    fun addItemModel(itemModel: ItemModel){
        _listOfItemModel.value = _listOfItemModel.value!! + itemModel
    }

    fun clearItemModelList(){
        _listOfItemModel.value = emptyList()
    }

    fun getListOfItemModel(): List<ItemModel>{
        return _listOfItemModel.value!!
    }

    fun getPokemons() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getListOfPokemonsUseCase(0, 25)
            if(!result.message.isNullOrEmpty())
                _getPokemonsErrorMessage.value = result.message!!
            else
                _getPokemonsResult.value = result.data!!

            _isLoading.value = false
        }
    }

    fun addToFavorites(pokeApiFavoritesModel: PokeApiFavoritesModel) {
        viewModelScope.launch {
            val result = addToFavoritesUseCase(pokeApiFavoritesModel)
            Log.d("resultAddToFavorites", result.toString())
            _addToFavoritesResult.value = result
        }
    }
    fun deleteFavorite(idPokemon: Int) {
        viewModelScope.launch {
            val result = deleteFavoriteUseCase(idPokemon)
            Log.d("resultDeleteFavorite", result.toString())
            _deleteFavoriteResult.value = result
        }
    }
    fun getDataPokemon(id: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getDataPokemonUseCase(id)
            if(!result.message.isNullOrEmpty()){
                _getPokemonsErrorMessage.value = result.message!!
                _isLoading.value = false
            }
            else{
                _getDataPokemonResult.value = result.data!!
                callToGetFormPokemonForDetail(id)
            }

        }
    }

    fun callToGetFormPokemonForDetail(id: Int){
        getFormPokemon(id, "")
    }

    fun callToGetFormPokemonForGetImage(id: Int, name: String){
        getFormPokemon(id, name)
    }

    @SuppressLint("SuspiciousIndentation")
    fun getFormPokemon(id: Int, name:String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getFormPokemonUseCase(id)
            if(!result.message.isNullOrEmpty()){
                _isLoading.value = false
                _getPokemonsErrorMessage.value = result.message!!
            } else {
                _getFormPokemonResult.value = result.data!!
                if(name.isNotEmpty()){
                    val itemModel = ItemModel(
                        id,
                        _getFormPokemonResult.value!!.sprites.front_default,
                        name
                    )
                    addItemModel(itemModel)
                }
            }
        }
    }


}