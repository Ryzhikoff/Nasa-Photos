package evgeniy.ryzhikov.remote.domain

import evgeniy.ryzhikov.remote.data.apod.repository.ApodRepository
import evgeniy.ryzhikov.remote.models.ApiResult
import javax.inject.Inject

class GetRandomPhotoUseCase @Inject constructor(
    private val repository: ApodRepository
) {

    suspend fun execute(): ApiResult<Any> =
        repository.getRandomPhoto()
}