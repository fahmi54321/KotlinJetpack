package com.example.kotlinjetpack

import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    // todo 1
    private var counter: Counter = Counter(0)

    // todo 2
    fun getCurrentCount(): Counter{
        return counter
    }

    // todo 3 (next MainActivity.kt)
    fun getUpdatedCount(): Int{
        return counter.count++

    }
}