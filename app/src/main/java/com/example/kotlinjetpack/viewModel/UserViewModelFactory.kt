package com.example.kotlinjetpack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinjetpack.room.UserRepository

// todo 12 (next UserViewModel.kt)

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}