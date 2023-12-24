package com.example.kotlinjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinjetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // todo 3
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // todo 4
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // todo 5 (finish)
        binding.btnSimpan.setOnClickListener {
            var data = binding.edtName.text.toString()
            binding.txtHelloWorld.text = data
        }
    }
}