package com.example.posts.di

import androidx.room.Room
import com.example.posts.data.PostRepository
import com.example.posts.data.db.PostDataBase
import com.example.posts.view.MainViewModel
import com.example.posts.view.PostAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single { PostRepository() }
    viewModel { MainViewModel(postRepository = get()) }
    factory { injectDoggoApiService() }
    single {
        Room.databaseBuilder(
            get(), PostDataBase::class.java,
            "PostDataBase.db"
        ).build()
    }
    single { PostAdapter() }

}