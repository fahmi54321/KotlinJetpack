package com.example.kotlinjetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// todo 1
class MainActivityViewModel(private var startingNum: Int): ViewModel() {

    // todo 2
    private var counter = MutableLiveData<Counter>()

    // todo 3
    val getCounter: LiveData<Counter>
        get() = counter


    init {
        counter.value = Counter(startingNum)
    }

    // todo 4 (next MainActivityViewModelFactory)
    fun setCounter(){
        var count = counter.value?.count
        count = count?.plus(1)
        counter.value = Counter(count?:0)
    }

}