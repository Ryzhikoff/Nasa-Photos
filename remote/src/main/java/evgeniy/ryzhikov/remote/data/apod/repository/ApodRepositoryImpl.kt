package evgeniy.ryzhikov.remote.data.apod.repository

import evgeniy.ryzhikov.remote.data.apod.ApodApi
import evgeniy.ryzhikov.remote.models.ApiResult
import javax.inject.Inject

class ApodRepositoryImpl @Inject constructor(
    private val retrofitService: ApodApi
) : ApodRepository {
    override suspend fun getRandomPhoto(): ApiResult<Any> =
        try {
            val response = retrofitService.getRandomPhoto()
            if (response.isSuccessful) {
                ApiResult.Success(response.body()!!)
            } else {
                ApiResult.Error(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            ApiResult.Error(e.toString())
        }

}