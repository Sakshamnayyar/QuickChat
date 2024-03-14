package com.app.quickchat.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app.quickchat.model.UserDatabase
import com.app.quickchat.model.data.User
import com.app.quickchat.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application): AndroidViewModel(application) {
    private val repository: UserRepository

    val list : LiveData<List<User>>?
        get() = repository.userList

    init{
        val userDB = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDB)
    }


    fun addUser(user: User){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertUser(user)
        }
    }
}