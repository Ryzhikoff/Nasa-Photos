package evgeniy.ryzhikov.core.models


data class ImageInfoUi(
    val imageUrl: String?,
    val imageUrlHD: String?,
    val title: String,
    val date: String,
    val description: String,
    var isFavorite: Boolean,
    val tags: List<String>?,
)