package com.example.viacinema.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.viacinema.R
import kotlinx.android.synthetic.main.content_main.*

class MainFragment : Fragment(R.layout.content_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())
        }

        registerButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToRegisterFragment())
        }
    }
}