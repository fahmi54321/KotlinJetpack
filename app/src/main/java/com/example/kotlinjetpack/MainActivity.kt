package com.example.kotlinjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinjetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    // todo 4
    private lateinit var factory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // todo 5 (finish)
        factory = MainActivityViewModelFactory(100)
        viewModel = ViewModelProvider(this,factory)[MainActivityViewModel::class.java]

        binding.counter = viewModel.getCurrentCount()

        binding.btnCounter.setOnClickListener {
            binding.counter = Counter(viewModel.getUpdatedCount())
        }


    }
}