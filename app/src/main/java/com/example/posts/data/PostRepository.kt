package com.example.posts.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.paging.rxjava2.observable
import com.example.posts.data.db.PostDao
import com.example.posts.data.db.PostDataBase
import com.example.posts.model.PostModel
import com.example.posts.repository.PostsApiService
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent.inject

@OptIn(ExperimentalPagingApi::class)
class PostRepository {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20

        //get doggo repository instance
        fun getInstance() = PostRepository()
    }

    private val postApiService: PostsApiService by inject(PostsApiService::class.java)
    private val db: PostDataBase? by inject(PostDataBase::class.java)


    fun letDoggoImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<PostModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { PostPagingSource(postApiService) }
        ).flow
    }


    //for live data users
    fun letDoggoImagesLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<PostModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { PostPagingSource(postApiService) }
        ).liveData
    }
    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }


}