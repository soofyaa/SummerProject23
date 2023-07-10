package com.itis.summerproject23

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.summerproject23.databinding.FragmentBeginBinding

class BeginFragment : Fragment(R.layout.fragment_begin) {

    private var binding: FragmentBeginBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences =
            requireActivity().getSharedPreferences("my_app", Context.MODE_PRIVATE)
        val isRegistered = sharedPreferences.getBoolean("is_registered", false)

        binding = FragmentBeginBinding.bind(view)
        binding?.run {
            btnWelcome.setOnClickListener {

                if (isRegistered) {
                    findNavController().navigate(R.id.action_beginFragment_to_userAccountFragment)
                } else {
                    findNavController().navigate(R.id.action_beginFragment_to_chooseFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}

