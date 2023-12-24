package com.example.kotlinjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinjetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // todo 5
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // todo 6
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // todo 7
        var user = User("Hello World","Fahmi")

        // todo 8 (finish)
        binding.user = user
    }
}