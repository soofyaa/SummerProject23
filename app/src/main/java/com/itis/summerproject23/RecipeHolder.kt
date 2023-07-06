package com.itis.summerproject23

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
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
            Glide.with(binding.root)
                .load(recipe.url)
                .placeholder(R.drawable.baseline_food_bank_24)
                .error(R.drawable.baseline_error_24)
                .into(ivCover)
        }
    }
}