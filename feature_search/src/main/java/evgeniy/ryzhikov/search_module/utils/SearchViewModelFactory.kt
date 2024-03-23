package evgeniy.ryzhikov.search_module.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import evgeniy.ryzhikov.core.domain.SettingProvider
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.IsFavoritesUseCase
import evgeniy.ryzhikov.remote.data.images.ImagesPageSource
import evgeniy.ryzhikov.remote.domain.SearchUseCase
import evgeniy.ryzhikov.search_module.ui.SearchViewModel

import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val isFavoritesUseCase: IsFavoritesUseCase,
    private val pagingSourceFactory: ImagesPageSource.Factory,
    private val settingProvider: SettingProvider,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(
                searchUseCase = searchUseCase,
                addToFavoriteUseCase = addToFavoriteUseCase,
                deleteFromFavoriteUseCase = deleteFromFavoriteUseCase,
                isFavoritesUseCase = isFavoritesUseCase,
                pagingSourceFactory = pagingSourceFactory,
                settingProvider = settingProvider,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}