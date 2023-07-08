package com.itis.summerproject23

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class UserAccountFragment : Fragment(R.layout.fragment_user_account) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferenceHelper = PreferenceHelper(requireContext())
        val savedUserName = preferenceHelper.getUserName()

        val greetingText = "Привет, $savedUserName!"
        val welcomeUserTextView = view.findViewById<TextView>(R.id.tv_welcome_user)
        welcomeUserTextView.text = greetingText

        val isLoggedIn = preferenceHelper.getIsLoggedIn()
        if (!isLoggedIn) {
            findNavController().navigate(R.id.action_userAccountFragment_to_chooseFragment)
        }

        val logoutButton = view.findViewById<Button>(R.id.btn_exit)
        logoutButton.setOnClickListener {
            preferenceHelper.clearUserData()
            findNavController().navigate(R.id.action_userAccountFragment_to_chooseFragment)
        }
    }
}