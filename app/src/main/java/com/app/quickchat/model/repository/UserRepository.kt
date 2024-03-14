package com.app.quickchat.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.quickchat.model.UserDao
import com.app.quickchat.model.data.User

class UserRepository(private val userDao: UserDao? = null) {

    val userList: LiveData<List<User>>? = userDao?.getAllUsers()


    suspend fun insertUser(user: User) {
        var dupe: User? = userDao?.get(user.phone)
        if(dupe != null)userDao?.delete(dupe)
        userDao?.insertUsers(user)
    }

}