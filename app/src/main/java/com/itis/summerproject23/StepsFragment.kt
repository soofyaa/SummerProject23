package com.itis.summerproject23

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.itis.summerproject23.databinding.FragmentStepsBinding

class StepsFragment : Fragment(R.layout.fragment_steps) {

    private var binding: FragmentStepsBinding? = null
    private var adapter: StepAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentStepsBinding.bind(view)
        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initAdapter() {
        adapter = StepAdapter(
            list = StepRepository.list,
            glide = Glide.with(this)
        )
        binding?.rvSteps?.adapter = adapter
    }
}