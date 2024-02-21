package evgeniy.ryzhiikov.feature_favorites.models

import evgeniy.ryzhikov.core.models.RandomPhotoUi
import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity

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