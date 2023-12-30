package com.example.kotlinjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinjetpack.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            val one = numberOne() // run pertama kali
            val two = numberTwo() // run setelah selesai yang pertama

            val result = one.plus(two) // run setelah selesai yang ketiga

            Log.e("TAG","Result : $result")
        }
    }

    private suspend fun numberOne(): Int{
        delay(7000)
        Log.e("TAG","numberOne is Done")
        return 11
    }

    private suspend fun numberTwo(): Int{
        delay(5000)
        Log.e("TAG","numberTwo is Done")
        return 22
    }
}