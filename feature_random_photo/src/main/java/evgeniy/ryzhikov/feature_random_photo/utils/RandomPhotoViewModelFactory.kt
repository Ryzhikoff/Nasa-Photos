package evgeniy.ryzhikov.feature_random_photo.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import evgeniy.ryzhikov.feature_random_photo.ui.RandomPhotoViewModel
import evgeniy.ryzhikov.remote.domain.GetRandomPhotoUseCase

import javax.inject.Inject

class RandomPhotoViewModelFactory @Inject constructor(
    private val getRandomPhotoUseCase: GetRandomPhotoUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        println("CatalogViewModelFactory")
        if (modelClass.isAssignableFrom(RandomPhotoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RandomPhotoViewModel(
                getRandomPhotoUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}