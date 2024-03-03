package evgeniy.ryzhikov.search_module.models

import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.database_module.data.room.entity.ImageInfoEntity

fun ImageInfoUi.toImageInfoEntity(): ImageInfoEntity =
    ImageInfoEntity(
        title = title,
        description = description,
        link = imageUrl ?: "",
        linkUhd = imageUrlHD ?: "",
        date = date,
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