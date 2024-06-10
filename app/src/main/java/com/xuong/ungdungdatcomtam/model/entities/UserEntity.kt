package com.xuong.ungdungdatcomtam.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    var userName: String,
    var password: String,
    var email: String,
    var role: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)