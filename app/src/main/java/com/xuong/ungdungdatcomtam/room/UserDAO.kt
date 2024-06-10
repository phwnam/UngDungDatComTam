package com.xuong.ungdungdatcomtam.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.xuong.ungdungdatcomtam.model.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Query("Select * From users")
    fun getAll(): Flow<List<UserEntity>>

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("Select * From users Where userName = :userName and password = :pass")
    fun getUserByUserNamePass(userName: String, pass: String) : Flow<UserEntity>

    @Query("Select * From users Where userName = :userName LIMIT 1")
    fun getUserByUsername(userName: String): UserEntity?

    @Upsert
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)
}