package evgeniy.ryzhikov.remote.data.dto


import com.google.gson.annotations.SerializedName

class ApodResultDtoList: ArrayList<ApodResultDto>()

data class ApodResultDto(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("hdurl")
    val hdUrl: String?,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("service_version")
    val serviceVersion: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)

