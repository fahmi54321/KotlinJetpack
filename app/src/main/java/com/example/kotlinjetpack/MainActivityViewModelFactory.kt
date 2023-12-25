package com.example.kotlinjetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// todo 1 (next MainActivityViewModel)
class MainActivityViewModelFactory(private var startingNum: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(startingNum) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}