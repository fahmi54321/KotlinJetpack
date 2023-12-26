package com.example.kotlinjetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// todo 9 (next UserDatabase.kt)

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDAO: UserDAO

    // singleton
    companion object{
        @Volatile
        private var INSTANCE: UserDatabase?= null
        fun getInstance(context: Context): UserDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){

                    // create database
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "users_db"
                    ).build()
                }

                return instance
            }
        }
    }
}