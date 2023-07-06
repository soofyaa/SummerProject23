package com.itis.summerproject23

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.summerproject23.databinding.ItemRecipeBinding

class RecipeAdapter(

    private var list: List<Recipe>,
    private val glide: RequestManager,
    private val onItemClick: (Recipe) -> Unit

) : RecyclerView.Adapter<RecipeHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeHolder = RecipeHolder(

        binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide = glide,
        onItemClick = onItemClick
    )

    override fun onBindViewHolder(
        holder: RecipeHolder,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<Recipe>) {
        list = newList
        notifyDataSetChanged()
    }
}