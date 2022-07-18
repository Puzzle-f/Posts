package com.example.posts.model

@Entity
data class PostModel(@PrimaryKey val id: String, val text: String, val likes:Int, val comments: Int)