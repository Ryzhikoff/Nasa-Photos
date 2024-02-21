package evgeniy.ryzhikov.core.models

import java.util.UUID

data class RandomPhotoUi(
    val uuid: String = UUID.randomUUID().toString(),
    val imageUrl: String?,
    val imageUrlHD: String?,
    val title: String,
    val date: String,
    val description: String,
    val isFavorite: Boolean,
    val tags: List<String>?,
)