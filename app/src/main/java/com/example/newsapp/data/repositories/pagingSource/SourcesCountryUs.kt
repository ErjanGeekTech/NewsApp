package com.example.newsapp.data.repositories.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.constants.Constants
import com.example.newsapp.data.network.apiservices.SourceApiService
import com.example.newsapp.models.Sources
import retrofit2.HttpException
import java.io.IOException

class SourcesCountryUs(
    private val service: SourceApiService,
) : PagingSource<Int, Sources>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Sources> {
        val position = params.key ?: Constants.NEWS_STARTING_PAGE_INDEX
        return try {
            val response = service.fetchSourcesCountryUs("us", position)
            val next = position + 1
            LoadResult.Page(
                data = response.sources,
                prevKey = null,
                nextKey = next
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Sources>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}