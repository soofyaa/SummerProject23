package com.itis.summerproject23

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.summerproject23.databinding.ItemStepBinding

class StepItem(
    private val binding: ItemStepBinding,
    private val glide: RequestManager,
) : RecyclerView.ViewHolder(binding.root)  {

    private val options: RequestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    fun onBind(step : Step) {
        binding.run {
            tvTitle.text = String.format(R.string.step_id.toString(), step.id)
            tvDesc.text = step.text

            glide.load(step.img_url)
                .placeholder(R.drawable.loading)
                .apply(options)
                .error(R.drawable.image_not_available)
                .into(ivImage)
        }
    }
}