package evgeniy.ryzhikov.core.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageInfoUi(
    val imageUrl: String?,
    val imageUrlHD: String?,
    val title: String,
    val date: String,
    val description: String,
    var isFavorite: Boolean,
    val tags: List<String>?,
): Parcelable