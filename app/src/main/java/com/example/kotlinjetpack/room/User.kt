package com.example.kotlinjetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// todo 7 (next UserDAO.kt)
@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Int,

    @ColumnInfo(name = "user_name")
    var name: String,

    @ColumnInfo(name = "user_email")
    var email: String,
)