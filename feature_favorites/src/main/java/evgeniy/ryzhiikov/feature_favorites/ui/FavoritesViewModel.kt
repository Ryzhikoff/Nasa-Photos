package evgeniy.ryzhiikov.feature_favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import evgeniy.ryzhiikov.feature_favorites.models.toImageInfoEntity
import evgeniy.ryzhiikov.feature_favorites.models.toRandomPhotoUi
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.GetAllFavoritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
) : ViewModel() {

    private val _favoriteList = MutableSharedFlow<List<ImageInfoUi>>()
    val favoriteList = _favoriteList.asSharedFlow()

    fun getFavoritesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getAllFavoritesUseCase.execute()
            _favoriteList.emit(list.map { it.toRandomPhotoUi() })
        }
    }

    fun deleteFromFavorites(randomPhotoUi: ImageInfoUi) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFromFavoriteUseCase.execute(randomPhotoUi.toImageInfoEntity())
        }
    }


    fun addToFavorite(randomPhotoUi: ImageInfoUi) {
        viewModelScope.launch(Dispatchers.IO) {
            addToFavoriteUseCase.execute(randomPhotoUi.toImageInfoEntity())
        }
    }
}