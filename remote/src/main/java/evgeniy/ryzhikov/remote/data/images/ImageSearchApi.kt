package evgeniy.ryzhikov.remote.data.images

import evgeniy.ryzhikov.remote.data.images.dto.SearchResultDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
API details [here](https://images.nasa.gov/docs/images.nasa.gov_api_docs.pdf)
 */
interface ImageApi {

    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("media_type") mediaType: MediaType = MediaType.image,
        @Query("page") page: Int,
        @Query("page_size") pageSize:Int,
    ): Response<SearchResultDto>
}

@Serializable
enum class MediaType {

    @SerialName("image")
    image,

    @SerialName("video")
    video,

    @SerialName("audio")
    audio,
}