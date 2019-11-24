package com.example.viacinema.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viacinema.usecase.UserDatabase

class LoginViewModel(private val db: UserDatabase) : ViewModel() {

    private val _loginStatus: MutableLiveData<Boolean> = MutableLiveData()

    val loginStatus: LiveData<Boolean> get() = _loginStatus

    fun onLoginClicked(user: String, pass: String) {
        val userMan = db.userDao().getUser(user)
        if (userMan == null) {
            _loginStatus.postValue(false)
        } else {
            if (userMan.password == pass) {
                //login succes
                _loginStatus.postValue(true)

            } else {
//                password incorect
                _loginStatus.postValue(false)
            }
        }

    }
}