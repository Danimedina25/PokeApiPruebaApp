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
import com.example.pokeapipruebaapp.pokemonList.domain.usecases.CheckIfIsFavoriteUseCase
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
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val checkIfIsFavoriteUseCase: CheckIfIsFavoriteUseCase
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

    val _deleteFavoriteResult = MutableLiveData<Int>(0)
    val deleteFavoriteResult: LiveData<Int>
        get() = _deleteFavoriteResult

    private val _checkIfIsFavoriteResult = MutableLiveData<Boolean>(false)
    val checkIfIsFavoriteResult : LiveData<Boolean>
        get() = _checkIfIsFavoriteResult

    private val _offset = MutableLiveData<Int>(0)
    val offset : LiveData<Int>
        get() = _offset
    private val _limit = MutableLiveData<Int>(25)
    val limit : LiveData<Int>
        get() = _limit

    fun addItemModel(itemModel: ItemModel){
        _listOfItemModel.value = _listOfItemModel.value!! + itemModel
    }

    fun clearItemModelList(){
        _listOfItemModel.value = emptyList()
    }

    fun clearAddToFavoritesResult(){
        _addToFavoritesResult.value = 0
    }
    fun clearDeleteFavoriteResult(){
        _deleteFavoriteResult.value = 0
    }

    fun getListOfItemModel(): List<ItemModel>{
        return _listOfItemModel.value!!
    }
    fun setOffset(offset: Int){
        _offset.value = offset
    }
    fun setLimit(limit: Int){
        _limit.value = limit
    }

    fun getPokemons() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getListOfPokemonsUseCase(_offset.value!!, _limit.value!!)
            if(!result.message.isNullOrEmpty())
                _getPokemonsErrorMessage.value = result.message!!
            else
                _getPokemonsResult.value = result.data!!

            _isLoading.value = false
        }
    }

    fun addToFavorites(pokeApiFavoritesModel: PokeApiFavoritesModel, position: Int) {
        viewModelScope.launch {
            val result = addToFavoritesUseCase(pokeApiFavoritesModel)
            Log.d("resultAddToFavorites", result.toString())
            _addToFavoritesResult.value = result
            if(getListOfItemModel().isNotEmpty())
                _listOfItemModel.value!![position].isFavorite = true
        }
    }
    fun deleteFavorite(idPokemon: Int, position: Int) {
        viewModelScope.launch {
            val result = deleteFavoriteUseCase(idPokemon)
            Log.d("resultDeleteFavorite", result.toString())
            _deleteFavoriteResult.value = result
            if(getListOfItemModel().isNotEmpty())
                _listOfItemModel.value!![position].isFavorite = false
        }
    }
    fun checkIfIsFavorite(idPokemon: Int) {
        viewModelScope.launch {
            val result = checkIfIsFavoriteUseCase(idPokemon)
            Log.d("resultcheckIfIsFavorite", result.toString())
            _checkIfIsFavoriteResult .value = result
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

    private fun callToGetFormPokemonForDetail(id: Int){
        getFormPokemon(id, "")
    }

    fun callToGetFormPokemonForGetImage(id: Int, name: String){
        getFormPokemon(id, name)
    }

    @SuppressLint("SuspiciousIndentation")
    fun getFormPokemon(id: Int, name:String) {
        clearItemModelList()
        _isLoading.value = true
        viewModelScope.launch {
            val result = getFormPokemonUseCase(id)
            if(!result.message.isNullOrEmpty()){
                _isLoading.value = false
                _getPokemonsErrorMessage.value = result.message!!
            } else {
                if(name.isNotEmpty()){
                    checkIfIsFavorite(id)
                    val itemModel = ItemModel(
                        id,
                        result.data!!.sprites.front_default,
                        name,
                        _checkIfIsFavoriteResult.value!!
                    )
                    addItemModel(itemModel)
                }
                _getFormPokemonResult.value = result.data!!
            }
        }
    }
}