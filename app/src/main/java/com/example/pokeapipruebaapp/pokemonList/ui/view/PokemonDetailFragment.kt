package com.example.pokeapipruebaapp.pokemonList.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.ui.text.capitalize
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapipruebaapp.R
import com.example.pokeapipruebaapp.adapter.ItemModelAdapter
import com.example.pokeapipruebaapp.databinding.FragmentPokemonDetailBinding
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokeApiFavoritesModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormModel
import com.example.pokeapipruebaapp.pokemonList.ui.view.adapters.ItemTypeAdapter
import com.example.pokeapipruebaapp.pokemonList.ui.viewmodel.ListOfPokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private val listOfPokemonsViewModel: ListOfPokemonsViewModel by viewModels()
    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: ItemTypeAdapter
    private lateinit var pokemonDataModel: PokemonDataModel
    private lateinit var pokemonFormModel: PokemonFormModel
    private val navigationArgs : PokemonDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonDataModel = navigationArgs.pokemonData
        pokemonFormModel = navigationArgs.pokemonForm

        listOfPokemonsViewModel.checkIfIsFavorite(pokemonFormModel.id)
        binding.noFavoriteButton.setOnClickListener{
            val favoritesModel = PokeApiFavoritesModel(pokemonFormModel.id, pokemonFormModel.name)
            listOfPokemonsViewModel.addToFavorites(favoritesModel, 0)
        }
        binding.favoriteButton.setOnClickListener{
            listOfPokemonsViewModel.deleteFavorite(pokemonFormModel.id, 0)
        }
        setData()
        setupObservers()
    }

    private fun setData(){
        binding.textName.text = pokemonFormModel.name.capitalize()
        Glide
            .with(requireContext())
            .load(pokemonFormModel.sprites.front_default)
            .placeholder(R.drawable.profile)
            .centerCrop()
            .into(binding.imagePokemon);
        binding.textHeight.text = "${pokemonDataModel.height} pies"
        binding.textWeight.text = "${pokemonDataModel.weight} libras"

        recyclerview = binding.recyclerView
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)
        adapter = ItemTypeAdapter(pokemonFormModel.types.listOfTypes)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
    private fun setupObservers(){
        listOfPokemonsViewModel.checkIfIsFavoriteResult.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.favoriteButton.visibility = View.VISIBLE
                binding.noFavoriteButton.visibility = View.GONE
            }
        })
        listOfPokemonsViewModel.addToFavoritesResult.observe(viewLifecycleOwner, Observer {
            if(it > 0){
                binding.noFavoriteButton.visibility = View.GONE
                binding.favoriteButton.visibility = View.VISIBLE
                Toast.makeText(requireContext(), "Se marcó a ${pokemonFormModel.name.capitalize()} como favorito!", LENGTH_SHORT).show()
            }
        })
        listOfPokemonsViewModel.deleteFavoriteResult.observe(viewLifecycleOwner, Observer {
            if(it > 0){
                binding.favoriteButton.visibility = View.GONE
                binding.noFavoriteButton.visibility = View.VISIBLE
                Toast.makeText(requireContext(), "Se desmarcó a ${pokemonFormModel.name.capitalize()} como favorito", LENGTH_SHORT).show()
            }
        })
    }
}