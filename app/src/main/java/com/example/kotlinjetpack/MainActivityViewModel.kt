package com.example.kotlinjetpack

import androidx.lifecycle.ViewModel

// todo 2
class MainActivityViewModel(private var startingNum: Int): ViewModel() {

    // todo 3 (next MainActivity)
    private var counter: Counter = Counter(startingNum)

    fun getCurrentCount(): Counter{
        return counter
    }

    fun getUpdatedCount(): Int{
        return counter.count++

    }
}