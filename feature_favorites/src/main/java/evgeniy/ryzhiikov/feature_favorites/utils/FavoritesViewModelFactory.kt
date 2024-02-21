package evgeniy.ryzhiikov.feature_favorites.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import evgeniy.ryzhiikov.feature_favorites.ui.FavoritesViewModel
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.GetAllFavoritesUseCase

import javax.inject.Inject

class FavoritesViewModelFactory @Inject constructor(
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        println("CatalogViewModelFactory")
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(
                deleteFromFavoriteUseCase = deleteFromFavoriteUseCase,
                getAllFavoritesUseCase = getAllFavoritesUseCase,
                addToFavoriteUseCase = addToFavoriteUseCase

            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}