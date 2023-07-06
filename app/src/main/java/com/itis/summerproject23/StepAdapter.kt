package com.itis.summerproject23

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.summerproject23.databinding.ItemStepBinding

class StepAdapter(
    private var list: List<Step>,
    private val glide: RequestManager,
) : RecyclerView.Adapter<StepItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StepItem = StepItem(
        binding = ItemStepBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        ),
        glide = glide,
    )

    override fun onBindViewHolder(holder: StepItem, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateDataset(newList: List<Step>) {
        list = newList
        notifyDataSetChanged()
    }
}