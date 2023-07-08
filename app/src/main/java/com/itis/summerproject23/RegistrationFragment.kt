package com.itis.summerproject23

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.itis.summerproject23.database.User
import com.itis.summerproject23.database.UsersDatabase

import com.itis.summerproject23.databinding.FragmentRegistrationBinding
import kotlinx.coroutines.launch


class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegistrationBinding.bind(view)
        binding.btnRegister.setOnClickListener {
            with(binding) {
                val preferenceHelper = PreferenceHelper(requireContext())
                val userName = etUsername.text.toString()
                val password = etPassword.text.toString()
                val email = etEmail.text.toString()
                if (userName.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    hideKeyboard()
                    Snackbar.make(view, "Введите все данные!", Snackbar.LENGTH_LONG)
                        .show()
                } else {
                    //preferenceHelper.saveUserName(userName)
                    Snackbar.make(view, "Пользователь сохранен!", Snackbar.LENGTH_LONG)
                        .apply { setAnchorView(R.id.bnv_main) }.show()
                    findNavController().navigate(R.id.action_registrationFragment_to_userAcoountFragment)
                    addToBase(userName, password, email)
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun addToBase(
        nameGet: String,
        passwordGet: String,
        emailGet: String
    ) {
        val newUser = User(
            name = nameGet,
            password = passwordGet,
            email = emailGet
        )
        context?.let {
            Room.databaseBuilder(it, UsersDatabase::class.java, "database-name")
                .allowMainThreadQueries()
                .build()
                .userDao()
        }?.insertUser(newUser)
    }

}


