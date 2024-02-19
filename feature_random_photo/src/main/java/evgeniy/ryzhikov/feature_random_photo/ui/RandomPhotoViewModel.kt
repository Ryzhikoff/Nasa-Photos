package evgeniy.ryzhikov.feature_random_photo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import evgeniy.ryzhikov.feature_random_photo.models.toRandomPhotoUi
import evgeniy.ryzhikov.remote.data.dto.ApodResultDtoList
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
    private val getRandomPhotoUseCase: GetRandomPhotoUseCase
) : ViewModel() {

    private val _randomPhoto = MutableSharedFlow<ApiResult<Any>>()
    val randomPhoto = _randomPhoto.asSharedFlow()


    fun getRandomPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getRandomPhotoUseCase.execute()

            result.doOnSuccess {
                val resultList = it as ApodResultDtoList
                val randomDto = resultList[Random.nextInt(0, resultList.size)]
                _randomPhoto.emit(ApiResult.Success(randomDto.toRandomPhotoUi()))
            }

            result.doOnError {
                _randomPhoto.emit(result)
            }
        }
    }

}