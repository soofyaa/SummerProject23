package com.itis.summerproject23

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class UserAcoountFragment : Fragment(R.layout.fragment_user_account) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferenceHelper = PreferenceHelper(requireContext())
        val savedUserName = preferenceHelper.getUserName()
        val greetingText = "Привет, $savedUserName!"
        val welcomeUserTextView = view.findViewById<TextView>(R.id.tw_welcomeUser)
        welcomeUserTextView.text = greetingText
    }
}