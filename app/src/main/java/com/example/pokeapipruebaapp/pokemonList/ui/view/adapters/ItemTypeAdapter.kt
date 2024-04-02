package com.example.pokeapipruebaapp.pokemonList.ui.view.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.ui.text.capitalize
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokeapipruebaapp.ItemModel
import com.example.pokeapipruebaapp.databinding.ItemMasterDetailBinding
import com.example.pokeapipruebaapp.databinding.ItemTypeBinding
import com.example.pokeapipruebaapp.pokemonList.domain.model.TypeModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.TypesModel

class ItemTypeAdapter(
    private val listTypes: List<TypesModel>,
    ):
    RecyclerView.Adapter<ItemTypeAdapter.MyViewHolder>() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val binding = ItemTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTypes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(listTypes[position]) {
                binding.txtType.text = this.type.name.capitalize()
            }
        }
    }

    inner class MyViewHolder(val binding: ItemTypeBinding): RecyclerView.ViewHolder(binding.root)

}