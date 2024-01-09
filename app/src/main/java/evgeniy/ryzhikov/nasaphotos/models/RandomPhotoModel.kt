package evgeniy.ryzhikov.nasaphotos.models

import evgeniy.ryzhikov.apod_module.api.ApodResultDto

data class RandomPhotoModel(
    val imageUrl: String?,
    val imageUrlHD: String?,
    val title: String,
    val date: String,
    val description: String
)

fun ApodResultDto.toRandomPhotoModel(): RandomPhotoModel =
    RandomPhotoModel(
        imageUrl = url,
        imageUrlHD = hdUrl,
        title = title,
        date = date,
        description = explanation
    )