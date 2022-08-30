package com.example.imagesearchapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imagesearchapp.data.remote.UnsplashApi
import com.example.imagesearchapp.model.UnsplashImage
import com.example.imagesearchapp.util.Constants.ITEM_PER_PAGE

class SearchPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String
) : PagingSource<Int, UnsplashImage>() {
    // <pageNumber, UnsplashImage>

    // Implement this method to trigger your async load (e.g. from database or network).
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val currentPage = params.key ?: 1
        return try {
            val response = unsplashApi.searchImages(query = query, perPage = ITEM_PER_PAGE)
            val endOfPaginationReached = response.images.isEmpty()
            if (response.images.isNotEmpty()) {
                LoadResult.Page(
                    data = response.images,
                    prevKey = if (currentPage == 1) null else currentPage -1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        // anchorPosition: Most recently accessed index in the list, including placeholders.
         return state.anchorPosition
    }

}