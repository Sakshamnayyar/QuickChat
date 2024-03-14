package com.app.quickchat.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users") data class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val phone: Long,
    val countryCode: String
)
