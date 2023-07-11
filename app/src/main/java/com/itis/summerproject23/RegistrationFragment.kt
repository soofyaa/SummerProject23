package com.itis.summerproject23

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.itis.summerproject23.database.User
import com.itis.summerproject23.database.UsersDatabase
import com.itis.summerproject23.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRegistrationBinding.bind(view)
        binding.btnRegister.setOnClickListener {
            with(binding) {
                val userName = etUsername.text.toString()
                val password = etPassword.text.toString()
                val email = etEmail.text.toString()
                if (userName.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    hideKeyboard()
                    Snackbar.make(view, getString(R.string.need_all_data), Snackbar.LENGTH_LONG)
                        .show()
                } else if (findUserByEmail(email) != null) {
                    Snackbar.make(view, getString(R.string.email_is_already_taken), Snackbar.LENGTH_LONG)
                        .show()
                }
                else {
                    val preferenceHelper = PreferenceHelper(requireContext())
                    preferenceHelper.setIsLoggedIn(true)
                    preferenceHelper.setUserName(userName)


                    Snackbar.make(view, getString(R.string.user_saved), Snackbar.LENGTH_LONG)
                        .apply { setAnchorView(R.id.bnv_main) }.show()


                    val sharedPreferences =
                        requireActivity().getSharedPreferences("my_app", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("is_registered", true)
                    editor.apply()

                    addToBase(userName, password, email)
                    findNavController()
                        .navigate(R.id.action_registrationFragment_to_userAccountFragment)
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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
            Room.databaseBuilder(it, UsersDatabase::class.java, "database-users")
                .allowMainThreadQueries()
                .build()
                .userDao()
        }?.insertUser(newUser)
    }

    private fun findUserByEmail(email: String) : User? {
        return context?.let {
            Room.databaseBuilder(it, UsersDatabase::class.java, "database-users")
                .allowMainThreadQueries()
                .build()
                .userDao()
        }?.getUserByEmail(email)
    }
}

