package com.example.kotlinjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinjetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // todo 6

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var factory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // todo 7 (next activity_main.xml)
        factory = MainActivityViewModelFactory(100)
        viewModel = ViewModelProvider(this,factory)[MainActivityViewModel::class.java]
        binding.myViewModel = viewModel

        viewModel.getCounter.observe(this) { observeCounter(it) }
    }

    private fun observeCounter(it: Counter?) {
        binding.counter = it
    }
}