package com.example.kotlinjetpack.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// todo 8 (next UserDatabase.kt)
@Dao
interface UserDAO {
    @Insert
    suspend fun insertUser(user: User): Long

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * from user")
    fun getAllUser(): LiveData<List<User>>
}