package com.xuong.ungdungdatcomtam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xuong.ungdungdatcomtam.room.UserDAO

class LoginViewModelFactory(private val userDAO: UserDAO) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(userDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}