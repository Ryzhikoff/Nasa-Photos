package evgeniy.ryzhikov.feature_random_photo.models

import evgeniy.ryzhikov.remote.data.dto.ApodResultDto


data class RandomPhotoUi(
    val imageUrl: String?,
    val imageUrlHD: String?,
    val title: String,
    val date: String,
    val description: String
)

fun ApodResultDto.toRandomPhotoUi(): RandomPhotoUi =
    RandomPhotoUi(
        imageUrl = url,
        imageUrlHD = hdUrl,
        title = title,
        date = date,
        description = explanation
    )