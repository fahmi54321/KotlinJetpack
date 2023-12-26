package com.example.kotlinjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinjetpack.databinding.ActivityMainBinding
import com.example.kotlinjetpack.room.User
import com.example.kotlinjetpack.room.UserDAO
import com.example.kotlinjetpack.room.UserDatabase
import com.example.kotlinjetpack.room.UserRepository
import com.example.kotlinjetpack.view.UserAdapter
import com.example.kotlinjetpack.viewModel.UserViewModel
import com.example.kotlinjetpack.viewModel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    // todo 21
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userViewModelFactory: UserViewModelFactory
    private lateinit var userDAO: UserDAO
    private lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // todo 22 (finish)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // room
        userDAO = UserDatabase.getInstance(application).userDAO

        // respository
        repository = UserRepository(userDAO)

        // view model
        userViewModelFactory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this,userViewModelFactory)[UserViewModel::class.java]

        // binding
        binding.userViewModel = userViewModel
        binding.lifecycleOwner = this // listen binding

        initRecyclerView()

    }

    private fun initRecyclerView(){
        binding.rvData.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        displayUserList()
    }

    private fun displayUserList(){
        userViewModel.users.observe(this, Observer {
            observeUser(it)
        })
    }

    private fun observeUser(it: List<User>?) {
        binding.rvData.adapter = UserAdapter(
            it?:ArrayList()
        ) {
            itemClicked(it)
        }
    }

    private fun itemClicked(it: User) {
        Toast.makeText(this, "${it.name}", Toast.LENGTH_SHORT).show()

        userViewModel.initUpdateAndDelete(it)
    }
}