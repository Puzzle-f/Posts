package com.example.posts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostModel(@PrimaryKey val id: String, val text: String, val likes:Int, val comments: Int)