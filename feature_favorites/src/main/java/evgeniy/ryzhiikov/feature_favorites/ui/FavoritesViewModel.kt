package evgeniy.ryzhiikov.feature_favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import evgeniy.ryzhiikov.feature_favorites.models.toImageInfoEntity
import evgeniy.ryzhiikov.feature_favorites.models.toRandomPhotoUi
import evgeniy.ryzhikov.core.models.RandomPhotoUi
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

    private val _favoriteList = MutableSharedFlow<List<RandomPhotoUi>>()
    val favoriteList = _favoriteList.asSharedFlow()

    fun getFavoritesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getAllFavoritesUseCase.execute()
            _favoriteList.emit(list.map { it.toRandomPhotoUi() })
        }
    }

    fun deleteFromFavorites(randomPhotoUi: RandomPhotoUi) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFromFavoriteUseCase.execute(randomPhotoUi.uuid)
        }
    }


    fun addToFavorite(randomPhotoUi: RandomPhotoUi) {
        viewModelScope.launch {
            addToFavoriteUseCase.execute(randomPhotoUi.toImageInfoEntity())
        }
    }
}