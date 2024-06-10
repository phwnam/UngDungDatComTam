package com.xuong.ungdungdatcomtam.repository

import com.xuong.ungdungdatcomtam.model.entities.UserEntity
import com.xuong.ungdungdatcomtam.room.UserDB

class UserRepository(val UserDB: UserDB) {
    suspend fun addUserToRoom(UserEntity: UserEntity){
        UserDB.UserDAO().insertUser(UserEntity)
    }
    fun getAllUser() = UserDB.UserDAO().getAll()

    suspend fun deleteUserToRoom(UserEntity: UserEntity){
        UserDB.UserDAO().deleteUser(UserEntity)
    }
    suspend fun updateUser(UserEntity: UserEntity){
        UserDB.UserDAO().updateUser(UserEntity)
    }
}