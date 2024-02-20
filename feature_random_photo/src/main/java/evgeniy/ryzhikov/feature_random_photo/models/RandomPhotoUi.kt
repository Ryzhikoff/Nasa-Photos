package evgeniy.ryzhikov.feature_random_photo.models

import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity
import evgeniy.ryzhikov.remote.data.dto.ApodResultDto
import java.util.UUID


data class RandomPhotoUi(
    val uuid: String = UUID.randomUUID().toString(),
    val imageUrl: String?,
    val imageUrlHD: String?,
    val title: String,
    val date: String,
    val description: String,
)

fun ApodResultDto.toRandomPhotoUi(): RandomPhotoUi =
    RandomPhotoUi(
        imageUrl = url,
        imageUrlHD = hdUrl,
        title = title,
        date = date,
        description = explanation,
    )

fun RandomPhotoUi.toImageInfoEntity(): ImageInfoEntity =
    ImageInfoEntity(
        title = title,
        description = description,
        link = imageUrl ?: "",
        linkUhd = imageUrlHD ?: "",
        date = date,
        uuid = uuid
    )