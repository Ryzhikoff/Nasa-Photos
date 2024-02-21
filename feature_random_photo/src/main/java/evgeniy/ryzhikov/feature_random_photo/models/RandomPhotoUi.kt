package evgeniy.ryzhikov.feature_random_photo.models

import evgeniy.ryzhikov.core.models.RandomPhotoUi
import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity
import evgeniy.ryzhikov.remote.data.dto.ApodResultDto


fun ApodResultDto.toRandomPhotoUi(): RandomPhotoUi =
    RandomPhotoUi(
        imageUrl = url,
        imageUrlHD = hdUrl,
        title = title,
        date = date,
        description = explanation,
        tags = null,
        isFavorite = false,
    )
fun RandomPhotoUi.toImageInfoEntity(): ImageInfoEntity =
    ImageInfoEntity(
        title = title,
        description = description,
        link = imageUrl ?: "",
        linkUhd = imageUrlHD ?: "",
        date = date,
        uuid = uuid,
        tags = tags?.asString()
    )

fun List<String>.asString(): String {
    val str = ""
    this.forEachIndexed { index, s ->
        str + s
        if (index < this.size) str + "|"
    }
    return str
}