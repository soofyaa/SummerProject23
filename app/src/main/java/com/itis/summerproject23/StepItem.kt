package com.itis.summerproject23

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.summerproject23.databinding.ItemStepBinding

class StepItem(
    private val binding: ItemStepBinding,
    private val glide: RequestManager,
) : RecyclerView.ViewHolder(binding.root) {

    private val options: RequestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

    fun onBind(step: Step) {
        binding.run {
            if (step.id == 0) {
                tvTitle.text = R.string.ingredients.toString()
                glide.load(R.drawable.empty_img)
                    .into(ivImage)
            } else {
                tvTitle.text = String.format(R.string.step_id.toString(), step.id)
                glide.load(step.img_url)
                    .placeholder(R.drawable.loading)
                    .circleCrop()
                    .apply(options)
                    .error(R.drawable.image_not_available)
                    .into(ivImage)
            }
            tvDesc.text = step.text
        }
    }
}