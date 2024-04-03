package com.example.pokeapipruebaapp.pokemonList.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapipruebaapp.R
import com.example.pokeapipruebaapp.adapter.ItemModelAdapter
import com.example.pokeapipruebaapp.databinding.FragmentListOfPokemonsBinding
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokeApiFavoritesModel
import com.example.pokeapipruebaapp.pokemonList.ui.viewmodel.ListOfPokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListOfPokemonsFragment : Fragment() {

    private val listOfPokemonsViewModel: ListOfPokemonsViewModel by viewModels()
    private var _binding: FragmentListOfPokemonsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: ItemModelAdapter
    var currentPosition: Int = 0
    private var itemSelected: Boolean = false
    private lateinit var favoritesModel: PokeApiFavoritesModel
    private var offset: Int = 0
    private var limit: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfPokemonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        offset = listOfPokemonsViewModel.offset.value!!
        limit = listOfPokemonsViewModel.limit.value!!
        listOfPokemonsViewModel.getPokemons()
        binding.btnSiguiente.setOnClickListener {
            getNextPokemons()
        }
        binding.btnAnterior.setOnClickListener {
            getPreviousPokemons()
        }
        setupObservers()
    }

    private fun setupObservers(){
        listOfPokemonsViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.loader.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }else{
                binding.loader.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        })
        listOfPokemonsViewModel.getPokemonsResult.observe(viewLifecycleOwner, Observer { it ->
            it!!.listOfPokemons.forEach {
                listOfPokemonsViewModel.callToGetFormPokemonForGetImage(it.id, it.name)
            }
        })


        listOfPokemonsViewModel.getFormPokemonResult.observe(viewLifecycleOwner, Observer {
            if(!itemSelected){
                if(listOfPokemonsViewModel.getListOfItemModel().size == 25){
                    // getting the recyclerview by its id
                    recyclerview = binding.recyclerView

                    // this creates a vertical layout Manager
                    recyclerview.layoutManager = LinearLayoutManager(context)
                    adapter = ItemModelAdapter(listOfPokemonsViewModel.getListOfItemModel(),
                        R.color.gray_light, R.color.green, requireContext().getDrawable(R.drawable.profile)!!,
                        ::onClickPokemon, true, ::onClickAddToFavorites, ::onClickDeleteFavorite
                    )
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter
                }

            }else{
                itemSelected = false
                findNavController().navigate(ListOfPokemonsFragmentDirections.actionListOfPokemonsFragmentToDetailPokemonFragment(listOfPokemonsViewModel.getDataPokemonResult.value!!, it))
            }
        })

        listOfPokemonsViewModel.addToFavoritesResult.observe(viewLifecycleOwner, Observer {
            if(it > 0){
                adapter.notifyItemChanged(currentPosition)
                //adapter.notifyDataSetChanged()
                Toast.makeText(requireContext(), "Se marcó a ${favoritesModel.name_pokemon.capitalize()} como favorito!",
                    Toast.LENGTH_SHORT
                ).show()
                listOfPokemonsViewModel.clearAddToFavoritesResult()
            }
        })
        listOfPokemonsViewModel.deleteFavoriteResult.observe(viewLifecycleOwner, Observer {
            if(it > 0){
                //adapter.notifyDataSetChanged()
                adapter.notifyItemChanged(currentPosition)
                Toast.makeText(requireContext(), "Se desmarcó a ${favoritesModel.name_pokemon.capitalize()} como favorito",
                    Toast.LENGTH_SHORT
                ).show()
                listOfPokemonsViewModel.clearDeleteFavoriteResult()
            }
        })
    }
    private fun onClickPokemon(id: Int){
        itemSelected = true
        listOfPokemonsViewModel.getDataPokemon(id)
    }

    private fun onClickAddToFavorites(position:Int, idPokemon: Int, namePokemon: String){
        currentPosition = position
        favoritesModel = PokeApiFavoritesModel(idPokemon, namePokemon)
        listOfPokemonsViewModel.addToFavorites(favoritesModel, position)
    }
    private fun onClickDeleteFavorite(position:Int, idPokemon: Int, namePokemon: String){
        currentPosition = position
        favoritesModel = PokeApiFavoritesModel(idPokemon, namePokemon)
        listOfPokemonsViewModel.deleteFavorite(idPokemon, position)
    }
    //private fun enable
    private fun getNextPokemons(){
        if(offset <= 1301){
            offset += 25
            listOfPokemonsViewModel.setOffset(offset)
            listOfPokemonsViewModel.clearItemModelList()
            listOfPokemonsViewModel.getPokemons()
        }else{
            Toast.makeText(requireContext(), "Has llegado al último pokemón de la lista!", LENGTH_SHORT).show()
        }
    }
    private fun getPreviousPokemons(){
        if(offset >= 25){
            offset -= 25
            listOfPokemonsViewModel.setOffset(offset)
            listOfPokemonsViewModel.clearItemModelList()
            listOfPokemonsViewModel.getPokemons()
        }else{
            Toast.makeText(requireContext(), "Estás en la primera página!", LENGTH_SHORT).show()
        }
    }
}