package evgeniy.ryzhiikov.feature_favorites.models

import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity

fun ImageInfoEntity.toRandomPhotoUi(): ImageInfoUi =
    ImageInfoUi(
        imageUrl = link,
        imageUrlHD = linkUhd,
        title = title,
        date = date,
        description = description,
        isFavorite = true,
        tags = tags?.toListString(),

    )

private fun String.toListString(): List<String> =
    this.split("|").toList()