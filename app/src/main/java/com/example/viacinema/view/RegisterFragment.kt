package com.example.viacinema.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.viacinema.MainActivity
import com.example.viacinema.R
import com.example.viacinema.usecase.User
import com.example.viacinema.usecase.UserDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.user_registration.*
import org.koin.android.ext.android.inject

class RegisterFragment : Fragment(R.layout.user_registration) {
    private val database: UserDatabase by inject()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmButton.visibility = View.GONE

        agreeTermsSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                confirmButton.visibility = View.VISIBLE
            } else {
                confirmButton.visibility = View.GONE
            }
        }

        Log.d("Oleg", "Total users: ${database.userDao().getAllUsers().size}")
        Log.d("Oleg", database.userDao().getUser("123")?.email)

        confirmButton.setOnClickListener {
            if (firstName.text.isNullOrEmpty() || lastName.text.isNullOrEmpty() || birthday.text.isNullOrEmpty() || address.text.isNullOrEmpty() ||
                username.text.isNullOrEmpty() || email.text.isNullOrEmpty() || password.text.isNullOrEmpty() || password.text.toString() != checkPassword.text.toString()
            ) {
                if (password.text.toString() != checkPassword.text.toString()) {
                    Snackbar.make(
                        constraintLayoutTag2,
                        "The password is not the same!",
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    Snackbar.make(
                        constraintLayoutTag2,
                        "You must complete all fields!",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                Log.d("Oleg", "\n first name: ${firstName.text.isNullOrEmpty()}")
                Log.d("Oleg", "\n last name: ${lastName.text.isNullOrEmpty()}")
                Log.d("Oleg", "\n birthday: ${birthday.text.isNullOrEmpty()}")
                Log.d("Oleg", "\n address: ${address.text.isNullOrEmpty()}")
                Log.d("Oleg", "\n username: ${username.text.isNullOrEmpty()}")
                Log.d("Oleg", "\n email: ${email.text.isNullOrEmpty()}")
                Log.d("Oleg", "\n password: ${password.text}")
                Log.d("Oleg", "\n checkPassword: ${checkPassword.text}")
            } else {
                database.userDao().insertUser(
                    User(
                        firstname = firstName.text.toString(),
                        lastname = lastName.text.toString(),
                        birthday = birthday.text.toString(),
                        address = address.text.toString(),
                        username = username.text.toString(),
                        email = email.text.toString(),
                        password = password.text.toString()
                    )
                )
                Log.d("Oleg", "Total users: ${database.userDao().getAllUsers()}")
                startActivity(Intent(activity!!, MainActivity::class.java))
            }
        }
    }
}