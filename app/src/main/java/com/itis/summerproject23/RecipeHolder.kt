package com.itis.summerproject23

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.summerproject23.database.Recipe
import com.itis.summerproject23.databinding.ItemRecipeBinding

class RecipeHolder(

    private val binding: ItemRecipeBinding,
    private val glide: RequestManager,
    private val onItemClick: (Recipe) -> Unit

) : RecyclerView.ViewHolder(binding.root) {

    private val options: RequestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    fun onBind(recipe: Recipe) {
        with(binding) {
            tvTitle.text = recipe.name
            root.setOnClickListener {
                onItemClick(recipe)
            }
            glide.load(recipe.url)
                .apply(options)
                .placeholder(R.drawable.loading)
                .error(R.drawable.baseline_error_24)
                .into(ivCover)
        }
    }
}