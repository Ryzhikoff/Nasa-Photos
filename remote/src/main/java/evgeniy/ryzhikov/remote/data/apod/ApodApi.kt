package evgeniy.ryzhikov.remote.data.apod

import evgeniy.ryzhikov.remote.data.Constants
import evgeniy.ryzhikov.remote.data.apod.dto.ApodResultDtoList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApi {

    @GET("planetary/apod")
    suspend fun getRandomPhoto(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("count") count: Int = 1
    ): Response<ApodResultDtoList>
}