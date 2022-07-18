package com.example.posts.repository

import com.example.posts.model.PostModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApiService {

    @GET("v1/images/search")
    suspend fun getPosts(@Query("page") page: Int, @Query("limit") size: Int): List<PostModel>

}