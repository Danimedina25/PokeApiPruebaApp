package com.example.pokeapipruebaapp.adapter

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

class ItemModelAdapter(
    private val listItems: List<ItemModel>,
    val backgroundColor: Int,
    val textColor: Int,
    val placeholderImage: Drawable,
    val onClickItem: (id: Int) -> Unit,
    val showOptionFavorites: Boolean
    ):
    RecyclerView.Adapter<ItemModelAdapter.MyViewHolder>() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val binding = ItemMasterDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(listItems[position]){
                if(showOptionFavorites)
                    binding.favoriteButton.visibility = View.VISIBLE
                if(this.url_image.isNotEmpty()){
                    binding.textName.text = this.nombre.capitalize()
                    showImageFromUrl(binding, this.url_image, this.nombre)
                }else{
                    if(this.nombre.isNotEmpty()){
                        if (startWithNumberOrSpecialCharacter(this.nombre.first().toString())){
                           showImagePlaceholder(binding, placeholderImage)
                        } else {
                           showIniciales(binding, this.nombre)
                        }
                    }else{
                        showImagePlaceholder(binding, placeholderImage)
                    }
                }
                binding.imageBackground.setOnClickListener{
                    onClickItem(this.id)
                }
            }
        }
    }

    inner class MyViewHolder(val binding: ItemMasterDetailBinding): RecyclerView.ViewHolder(binding.root)

    private fun startWithNumberOrSpecialCharacter(text: String): Boolean {
        return (text.contains(Regex("[^A-Za-z]")))
    }

    private fun showImageFromUrl(binding: ItemMasterDetailBinding, urlImage: String, nombre: String){
        binding.textIniciales.visibility = View.GONE
        binding.imageProfile.visibility = View.VISIBLE
        val cardColor = ContextCompat.getColor(binding.imageProfile.context!!, backgroundColor)
        binding.imageBackground.setCardBackgroundColor(cardColor)
        Glide
            .with(binding.imageBackground.context)
            .load(urlImage)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    if(!nombre.isNullOrEmpty()){
                        if (startWithNumberOrSpecialCharacter(nombre.first().toString())){
                            showImagePlaceholder(binding, placeholderImage)
                        } else {
                            showIniciales(binding, nombre)
                        }
                    }else{
                        showImagePlaceholder(binding, placeholderImage)
                    }
                    return false
                }

            })
            .centerCrop()
            .into(binding.imageProfile)
    }

    private fun showIniciales(binding: ItemMasterDetailBinding, nombre: String){
        binding.textIniciales.visibility = View.VISIBLE
        binding.imageProfile.visibility = View.GONE
        binding.textIniciales.text = getIniciales(nombre)
        binding.textIniciales.setTextColor(AppCompatResources.getColorStateList(binding.textIniciales.context, textColor))
        val cardColor = ContextCompat.getColor(binding.imageProfile.context!!, backgroundColor)
        binding.imageBackground.setCardBackgroundColor(cardColor)
    }

    private fun showImagePlaceholder(binding: ItemMasterDetailBinding, image: Drawable){
        binding.textIniciales.visibility = View.GONE
        binding.imageProfile.visibility = View.VISIBLE
        binding.imageProfile.setImageDrawable(placeholderImage)
    }

    private fun getIniciales(nombre: String): String{
        var iniciales = ""
        for (s in nombre.split(" ")) {
            iniciales += s[0].uppercase()
        }
        println(iniciales)
        return iniciales
    }
}