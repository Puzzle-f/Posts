package com.example.posts.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.posts.data.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainViewModel(
    val postRepository: PostRepository
) : ViewModel() {

    fun fetchDoggoImagesLiveData(): LiveData<PagingData<String>> {
        return postRepository.letDoggoImagesLiveData()
            .map { it.map { it.text } }
            .cachedIn(viewModelScope)
    }

    fun fetchDoggoImages(): Flow<PagingData<String>> {
        return postRepository.letDoggoImagesFlow()
            .map { it.map { it.text } }
            .cachedIn(viewModelScope)
    }


}