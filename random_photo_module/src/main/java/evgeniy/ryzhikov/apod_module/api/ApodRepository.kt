package evgeniy.ryzhikov.apod_module.api


import evgeniy.ryzhikov.utils.models.ApiResult
import javax.inject.Inject

class ApodRepository @Inject constructor(private val retrofitService: ApodApi) {

    suspend fun getRandomPhoto(): ApiResult<Any> =
        try {
            val response = retrofitService.getRandomPhotoFromApi()
            if (response.isSuccessful) {
                ApiResult.Success(response.body()!!)
            } else {
                ApiResult.Error(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            ApiResult.Error(e.toString())
        }
}