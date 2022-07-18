package com.example.posts.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.posts.data.PostRepository.Companion.DEFAULT_PAGE_INDEX
import com.example.posts.model.PostModel
import com.example.posts.repository.PostsApiService
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class PostPagingSource(val posstsApiService: PostsApiService) :
    PagingSource<Int, PostModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostModel> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = posstsApiService.getPosts(page, params.loadSize)
            LoadResult.Page(
                response, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PostModel>): Int? {
        TODO("Not yet implemented")
    }
}