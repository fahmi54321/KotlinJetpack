package com.example.kotlinjetpack.room

// todo 10 (next UserViewModel.kt)

class UserRepository(private val dao: UserDAO) {

    val users = dao.getAllUser()

    suspend fun insert(user: User): Long{
        return dao.insertUser(user)
    }

    suspend fun delete(user: User){
        return dao.deleteUser(user)
    }

    suspend fun update(user: User){
        return dao.updateUser(user)
    }

    suspend fun deleteAll(){
        return dao.deleteAll()
    }
}