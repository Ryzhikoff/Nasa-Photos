package evgeniy.ryzhikov.remote.domain

import evgeniy.ryzhikov.remote.data.repository.NasaRepository
import evgeniy.ryzhikov.remote.models.ApiResult
import javax.inject.Inject

class GetRandomPhotoUseCase @Inject constructor(
    private val repository: NasaRepository
) {

    suspend fun execute(): ApiResult<Any> =
        repository.getRandomPhoto()
}