package evgeniy.ryzhikov.apod_module.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApi {
    @GET("planetary/apod")
    suspend fun getRandomPhotoFromApi(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("count") count: Int = 1
    ): Response<ApodResultDtoList>
}

