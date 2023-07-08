package com.itis.summerproject23

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.summerproject23.databinding.FragmentChooseBinding

class ChooseFragment : Fragment(R.layout.fragment_choose) {
    private var _binding: FragmentChooseBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentChooseBinding.bind(view)

        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_chooseFragment_to_entryFragment)
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_chooseFragment_to_registrationFragment)
        }
    }
}