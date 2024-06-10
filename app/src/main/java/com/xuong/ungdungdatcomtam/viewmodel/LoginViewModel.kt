package com.xuong.ungdungdatcomtam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xuong.ungdungdatcomtam.model.entities.UserEntity
import com.xuong.ungdungdatcomtam.room.UserDAO
import kotlinx.coroutines.launch

class LoginViewModel(private val dao: UserDAO) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _isAuthenticated = MutableLiveData<Boolean?>()
    val isAuthenticated: LiveData<Boolean?> = _isAuthenticated

    private val _isShowPassword = MutableLiveData<Boolean>()
    val isShowPassword: LiveData<Boolean> = _isShowPassword

    private val _isRole = MutableLiveData<Int>()
    val isRole: LiveData<Int> = _isRole

    fun insertSampleAdmin(){
        viewModelScope.launch {
            var users: List<UserEntity> = listOf()
            dao.getAll().collect(){
                users = it
                if(users.isEmpty()){
                    dao.insertUser(
                        UserEntity(
                            userName = "admin",
                            password = "admin",
                            "admin@gmail.com",
                            0
                        )
                    )
                    dao.insertUser(
                        UserEntity(
                            userName = "nam",
                            password = "123",
                            "nam@gmail.com",
                            0
                        )
                    )
                    dao.insertUser(
                        UserEntity(
                            userName = "quynh",
                            password = "123",
                            "quynh@gmail.com",
                            0
                        )
                    )
                    dao.insertUser(
                        UserEntity(
                            userName = "vu",
                            password = "123",
                            "vu@gmail.com",
                            0
                        )
                    )
                }
            }
        }
    }
    fun login(username: String, password: String){
        viewModelScope.launch{
            val user = dao.getUserByUserNamePass(username, password)
            user.collect{
                if(it != null){
                    _isAuthenticated.value = true
                    _isRole.value = it.role
                }else{
                    _isAuthenticated.value = false
                }
            }
        }
    }

    fun resetAuthenticationState(){
        _isAuthenticated.value = null
    }
}

