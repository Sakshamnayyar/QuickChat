package com.app.quickchat.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.quickchat.model.data.User

@Dao
interface UserDao {
    @Query("SELECT * from users")
    fun getAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user: User)

    @Query("SELECT * from users WHERE phone = :phone")
    suspend fun get(phone: Long): User

    @Delete
    fun delete(user:User)
}