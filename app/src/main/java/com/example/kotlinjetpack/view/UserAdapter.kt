package com.example.kotlinjetpack.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinjetpack.R
import com.example.kotlinjetpack.databinding.ItemBinding
import com.example.kotlinjetpack.room.User

class UserAdapter(private var users: List<User>,private var clickListener: (User) -> Unit) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: User, clickListener: (User) -> Unit){
            binding.txtNama.text = user.name
            binding.txtEmail.text = user.email

            binding.root.setOnClickListener {
                clickListener(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item,parent,false)

        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position],clickListener)
    }
}