package com.example.kotlinjetpack.viewModel

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinjetpack.room.User
import com.example.kotlinjetpack.room.UserRepository
import kotlinx.coroutines.launch

// todo 11 (next UserViewModelFactory.kt)

class UserViewModel(private val repository: UserRepository): ViewModel(), Observable {

    // todo 13
    val users = repository.users

    private var isUpdateOrDelete = false

    private lateinit var userToUpdateOrDelete: User

    // todo 14 (binding untuk layout)

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEMail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    // todo 15 (method, next activity_main.xml)
    fun initUpdateAndDelete(user: User) {
        inputName.value = user.name
        inputEMail.value = user.email
        isUpdateOrDelete = true
        userToUpdateOrDelete = user
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    fun saveOrUpdate(){
        if(isUpdateOrDelete){
            userToUpdateOrDelete.name = inputName.value?:""
            userToUpdateOrDelete.email = inputEMail.value?:""

            update(userToUpdateOrDelete)
        }else{
            val name = inputName.value
            val email = inputEMail.value

            Log.e("data","name: $name, email: $email")

            insert(User(0,name?:"",email?:""))

            inputName.value = ""
            inputEMail.value = ""
        }
    }

    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(userToUpdateOrDelete)
        }else{
            clearAll()

        }
    }

    private fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    private fun update(user: User) = viewModelScope.launch {
        repository.update(user)

        inputName.value = ""
        inputEMail.value = ""
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    private fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    private fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)

        inputName.value = ""
        inputEMail.value = ""
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}