

package com.wisdomleaf.unsplash.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wisdomleaf.unsplash.model.UnsplashItem
import com.wisdomleaf.unsplash.networking.RemoteApiService
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

//UnsplashPagingSource acts as a data source, corresponding to the Model layer in the MVVM.
class UnsplashPagingSource(private val remoteApiService: RemoteApiService) :
    PagingSource<Int, UnsplashItem>() {

    // The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
    override fun getRefreshKey(state: PagingState<Int, UnsplashItem>): Int? {
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashItem> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            /*RemoteApiService is used to fetch the list of images from the API,
            passing pageIndex as a parameter, via LoadParams.*/
            val response = remoteApiService.fetchImages(
                page = pageIndex
            )
            val unsplashItems = response.body()
            val nextKey =
                if (unsplashItems?.isEmpty() == true) {
                    null
                } else {
                    //not to request duplicate items after first time
                    pageIndex + (params.loadSize / LATENT_PAGE_SIZE)
                }
            LoadResult.Page(
                data = unsplashItems ?: listOf(),
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}