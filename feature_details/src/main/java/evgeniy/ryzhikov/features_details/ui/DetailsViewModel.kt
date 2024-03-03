package evgeniy.ryzhikov.features_details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
import evgeniy.ryzhikov.features_details.models.toImageInfoEntity
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class DetailsViewModel @Inject constructor(
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
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

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val viewModerProvider: Provider<DetailsViewModel>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == DetailsViewModel::class.java)
            return viewModerProvider.get() as T
        }
    }

}