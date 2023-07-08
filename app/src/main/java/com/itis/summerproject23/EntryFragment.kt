package com.itis.summerproject23

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.itis.summerproject23.database.UsersDatabase
import com.itis.summerproject23.databinding.FragmentEntryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EntryFragment : Fragment(R.layout.fragment_entry) {

    private var _binding: FragmentEntryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentEntryBinding.bind(view)
        binding.btnEntry.setOnClickListener {
            with(binding) {

                val userName = etUsername.text.toString()
                val password = etPassword.text.toString()

                if (userName.isEmpty() || password.isEmpty()) {
                    hideKeyboard()
                    Snackbar.make(view, getString(R.string.need_all_data), Snackbar.LENGTH_LONG)
                        .show()
                } else {
                    login(userName, password)
                    val preferenceHelper = PreferenceHelper(requireContext())
                    preferenceHelper.setUserName(userName)
                    preferenceHelper.setIsLoggedIn(true)
                    Snackbar.make(view, getString(R.string.done), Snackbar.LENGTH_LONG)
                        .apply { setAnchorView(R.id.bnv_main) }.show()
                }
            }
        }
    }

    private fun login(username: String, password: String) {
        val userDao = UsersDatabase.getInstance(requireContext()).userDao()

        viewLifecycleOwner.lifecycleScope.launch {
            val user = withContext(Dispatchers.IO) {
                userDao.getUserByUsernameAndPassword(username, password)
            }

            if (user != null) {
                findNavController().navigate(R.id.action_entryFragment_to_userAccountFragment)
            } else {
                view?.let {
                    hideKeyboard()
                    Snackbar.make(it, getString(R.string.no_user), Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}