package evgeniy.ryzhikov.feature_random_photo.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
import evgeniy.ryzhikov.feature_random_photo.ui.RandomPhotoViewModel
import evgeniy.ryzhikov.remote.domain.GetRandomPhotoUseCase
import java.security.PrivateKey

import javax.inject.Inject

class RandomPhotoViewModelFactory @Inject constructor(
    private val getRandomPhotoUseCase: GetRandomPhotoUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        println("CatalogViewModelFactory")
        if (modelClass.isAssignableFrom(RandomPhotoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RandomPhotoViewModel(
                getRandomPhotoUseCase,
                addToFavoriteUseCase,
                deleteFromFavoriteUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}