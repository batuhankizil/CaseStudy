package com.example.study.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.study.R
import com.example.study.databinding.FragmentLoginBinding
import com.example.study.sharedPreferences.LoginDataSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    @Inject
    lateinit var loginDataSource: LoginDataSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.login.setOnClickListener {
            val username = binding.username.text.toString()

            if (username.isNotEmpty()) {
                loginDataSource.isLoggedIn = true
                loginDataSource.userName = username
                findNavController().navigate(R.id.homePageFragment)
            } else {
                Toast.makeText(context, "Cannot be empty", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
}