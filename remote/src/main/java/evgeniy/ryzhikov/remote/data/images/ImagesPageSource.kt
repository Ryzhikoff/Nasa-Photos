package evgeniy.ryzhikov.remote.data.images

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.remote.models.toImageInfoUi
import retrofit2.HttpException

class ImagesPageSource @AssistedInject constructor(
    private val imageApi: ImageApi,
    @Assisted("query") private val query: String,
) : PagingSource<Int, ImageInfoUi>() {
    override fun getRefreshKey(state: PagingState<Int, ImageInfoUi>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageInfoUi> {
        val page = params.key ?: INITIAL_PAGE_NUMBER
        val pageSize = params.loadSize
        val response = imageApi.search(query = query, page = page, pageSize = pageSize)
        return if (response.isSuccessful) {
            val itemDto = checkNotNull(response.body()?.collection?.items).map { it.toImageInfoUi() }
            val nextKey = if (itemDto.size < pageSize) null else page + 1
            val prevKer = if (page == 1) null else page -1
            LoadResult.Page(data = itemDto, prevKey = prevKer, nextKey = nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("query") query: String): ImagesPageSource
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }

}
