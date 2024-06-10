package com.xuong.ungdungdatcomtam.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xuong.ungdungdatcomtam.model.entities.UserEntity

@Database(entities = [UserEntity:: class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase(){
    abstract fun UserDAO(): UserDAO

    companion object{
        @Volatile
        private var INTANCE: UserDB? = null
        fun getIntance(context: Context): UserDB{
            synchronized(this){
                var intance = INTANCE
                if(intance == null){
                    intance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDB::class.java,
                        "user_db1"
                    ).build()
                }
                return intance
            }
        }
    }
}