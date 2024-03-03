package evgeniy.ryzhikov.search_module.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.IsFavoritesUseCase
import evgeniy.ryzhikov.remote.data.images.ImagesPageSource
import evgeniy.ryzhikov.remote.domain.SearchUseCase
import evgeniy.ryzhikov.search_module.models.toImageInfoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val isFavoritesUseCase: IsFavoritesUseCase,
    private val pagingSourceFactory: ImagesPageSource.Factory,
) : ViewModel() {

    fun toFavorite(isAdd: Boolean, imageInfoUi: ImageInfoUi) {
        viewModelScope.launch {
            if (isAdd) {
                addToFavoriteUseCase.execute(imageInfoUi.toImageInfoEntity())
            } else {
                deleteFromFavoriteUseCase.execute(imageInfoUi.toImageInfoEntity())
            }
        }

    }

    val query: MutableStateFlow<String> = MutableStateFlow("")

    fun setQuery(query: String) {
        this.query.tryEmit(query)
    }

    val searchResult: StateFlow<PagingData<ImageInfoUi>> = query
        .filter { it.isNotEmpty() }
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    private fun newPager(query: String): Pager<Int, ImageInfoUi> =
        Pager(
            PagingConfig(
                pageSize = 20,
                initialLoadSize = 30,
                maxSize = 100,
            )
        ) {
            pagingSourceFactory.create(query)
        }

}

