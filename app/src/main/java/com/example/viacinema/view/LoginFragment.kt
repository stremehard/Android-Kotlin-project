package com.example.viacinema.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.viacinema.R
import com.example.viacinema.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.log_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.log_in) {
    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confirmLoginButton.setOnClickListener {

            viewModel.onLoginClicked(
                usernameLogin.text.toString(),
                passwordLogin.text.toString()
            )            // LOGIN CODE
        }
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.loginStatus.observe(viewLifecycleOwner, Observer {
            if (it)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMoviesListFragment())
        })
    }
}