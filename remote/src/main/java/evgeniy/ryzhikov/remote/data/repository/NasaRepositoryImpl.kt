package evgeniy.ryzhikov.remote.data.repository

import evgeniy.ryzhikov.remote.data.NasaApi
import evgeniy.ryzhikov.remote.models.ApiResult
import javax.inject.Inject

class NasaRepositoryImpl @Inject constructor(
    private val retrofitService: NasaApi
) : NasaRepository {
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