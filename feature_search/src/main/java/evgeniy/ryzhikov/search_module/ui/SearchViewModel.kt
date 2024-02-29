package evgeniy.ryzhikov.search_module.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.core.ui.rv.PaginationParams.PAGES_BUFFER_SIZE
import evgeniy.ryzhikov.core.ui.rv.PaginationParams.PAGE_SIZE
import evgeniy.ryzhikov.core.ui.rv.PaginationParams.VISIBLE_DIFF
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.IsFavoritesUseCase
import evgeniy.ryzhikov.remote.data.images.dto.SearchResultDto
import evgeniy.ryzhikov.remote.domain.SearchUseCase
import evgeniy.ryzhikov.remote.models.ApiResult
import evgeniy.ryzhikov.remote.models.doOnError
import evgeniy.ryzhikov.remote.models.doOnSuccess
import evgeniy.ryzhikov.search_module.models.toImageInfoEntity
import evgeniy.ryzhikov.search_module.models.toListImageInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val isFavoritesUseCase: IsFavoritesUseCase,
) : ViewModel() {

    private val _searchResult: MutableSharedFlow<ApiResult<Any>> = MutableSharedFlow()
    val searchResult: SharedFlow<ApiResult<Any>> get() = _searchResult.asSharedFlow()


    private var totalPages = 0
    private var currentPage = 0
    var isLoading = false
    var addPageIsNext = true
    private var lastPaginationIsPrevious = false

    fun search(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchUseCase.execute(query = text, page = 1, pageSize = PAGE_SIZE).also { apiResult ->
                apiResult.doOnSuccess { searchResultDto ->
                    searchResultDto as SearchResultDto

                    currentPage = 1
                    totalPages = ceil(searchResultDto.collection.metadata.totalHits.toDouble() / PAGE_SIZE).toInt()

                    onSuccess(searchResultDto)
                }
                apiResult.doOnError {
                    _searchResult.emit(ApiResult.Error(it))
                }
            }
        }
    }

    fun doSearchPagination(
        visibleItemCount: Int,
        totalItemCount: Int,
        firstVisibleItemCount: Int,
        query: String,
        isNext: Boolean,
    ) {
        if (!isLoading) {
            var nextPage = 0
            if (isNext) {
                nextPage = currentPage + if (lastPaginationIsPrevious) PAGES_BUFFER_SIZE else 1
                if (((visibleItemCount + firstVisibleItemCount) >= totalItemCount - VISIBLE_DIFF) && nextPage <= totalPages) {
                    lastPaginationIsPrevious = false
                    println(
                        "isNext -  $visibleItemCount firstVisibleItemCount $firstVisibleItemCount totalItemCount $totalItemCount" +
                                " totalItemCount - VISIBLE_DIFF ${totalItemCount - VISIBLE_DIFF} " +
                                "currentPage $currentPage nextPage $nextPage"
                    )
                } else {
                    return
                }
            } else {
                nextPage = currentPage - if (lastPaginationIsPrevious) 1 else PAGES_BUFFER_SIZE
                if (firstVisibleItemCount < VISIBLE_DIFF && nextPage > 0) {
                    lastPaginationIsPrevious = true
                    println(
                        "isPrev -  $visibleItemCount firstVisibleItemCount $firstVisibleItemCount totalItemCount $totalItemCount" +
                                "currentPage $currentPage nextPage $nextPage"
                    )
                } else {
                    return
                }
            }

            isLoading = true
            addPageIsNext = isNext

            viewModelScope.launch(Dispatchers.IO) {
                searchUseCase.execute(query, nextPage, PAGE_SIZE).also { apiResult ->
                    println("paginationload coplete newPage: $nextPage")
                    apiResult.doOnSuccess { searchResultDto ->
                        currentPage = nextPage
                        searchResultDto as SearchResultDto
                        onSuccess(searchResultDto)
                    }

                    apiResult.doOnError {
                        _searchResult.emit(ApiResult.Error(it))
                    }
                }
            }
        }

    }

    private suspend fun onSuccess(searchResultDto: SearchResultDto) {
        val listImageInfo = searchResultDto.toListImageInfo()

        listImageInfo.map { imageInfoUi ->
            imageInfoUi.isFavorite = isFavoritesUseCase.execute(imageInfoUi.toImageInfoEntity())
        }

        _searchResult.emit(
            ApiResult.Success(listImageInfo)
        )
    }

    fun toFavorite(isAdd: Boolean, imageInfoUi: ImageInfoUi) {
        viewModelScope.launch {
            if (isAdd) {
                addToFavoriteUseCase.execute(imageInfoUi.toImageInfoEntity())
            } else {
                deleteFromFavoriteUseCase.execute(imageInfoUi.toImageInfoEntity())
            }
        }

    }


}