package com.example.newsapp.data.repositories.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.constants.Constants.NEWS_STARTING_PAGE_INDEX
import com.example.newsapp.data.network.apiservices.EverythingApiService
import com.example.newsapp.models.EverythingModel
import retrofit2.HttpException
import java.io.IOException


class EverythingPagingSource(
    private val service: EverythingApiService,
) : PagingSource<Int, EverythingModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EverythingModel> {
        val position = params.key ?: NEWS_STARTING_PAGE_INDEX
        return try {
            val response = service.fetchEverything("bitcoin", position)
            val next = position + 1
            LoadResult.Page(
                data = response.articles,
                prevKey = null,
                nextKey = next
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, EverythingModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}