package evgeniy.ryzhikov.feature_random_photo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.database_module.domain.AddToFavoriteUseCase
import evgeniy.ryzhikov.database_module.domain.DeleteFromFavoriteUseCase
import evgeniy.ryzhikov.feature_random_photo.models.toImageInfoEntity
import evgeniy.ryzhikov.feature_random_photo.models.toImageInfoUi
import evgeniy.ryzhikov.remote.data.apod.dto.ApodResultDtoList
import evgeniy.ryzhikov.remote.domain.GetRandomPhotoUseCase
import evgeniy.ryzhikov.remote.models.ApiResult
import evgeniy.ryzhikov.remote.models.doOnError
import evgeniy.ryzhikov.remote.models.doOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class RandomPhotoViewModel @Inject constructor(
    private val getRandomPhotoUseCase: GetRandomPhotoUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
) : ViewModel() {

    private val _randomPhoto = MutableSharedFlow<ApiResult<Any>>()
    val randomPhoto = _randomPhoto.asSharedFlow()


    fun getRandomPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getRandomPhotoUseCase.execute()

            result.doOnSuccess {
                val resultList = it as ApodResultDtoList
                val randomDto = resultList[Random.nextInt(0, resultList.size)]
                _randomPhoto.emit(ApiResult.Success(randomDto.toImageInfoUi()))
            }

            result.doOnError {
                _randomPhoto.emit(result)
            }
        }
    }

    fun toFavorite(isAdd: Boolean, randomPhotoUi: ImageInfoUi) {
        viewModelScope.launch {
            if (isAdd) {
                addToFavoriteUseCase.execute(randomPhotoUi.toImageInfoEntity())
            } else {
                deleteFromFavoriteUseCase.execute(randomPhotoUi.toImageInfoEntity())
            }
        }

    }

}