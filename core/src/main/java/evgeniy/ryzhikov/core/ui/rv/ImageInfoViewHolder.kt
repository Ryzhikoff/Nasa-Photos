package evgeniy.ryzhikov.core.ui.rv

import androidx.recyclerview.widget.RecyclerView
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.core.ui.customview.ImageDetailCard

class ImageInfoViewHolder(
    private val imageDetailCard: ImageDetailCard,
): RecyclerView.ViewHolder(imageDetailCard) {

    fun init (randomPhotoUi: ImageInfoUi) {
        imageDetailCard.setData(randomPhotoUi)

    }

    fun setOnFavoriteClickListener(callback: (Boolean) -> Unit) {
        imageDetailCard.setOnFavoritesClickListener {
            callback(it)
        }
    }

    fun setOnItemClickListener(callback: () -> Unit) {
        imageDetailCard.setOnClickListener {
            callback()
        }
    }

    fun setOnTagClickListener(callback: (String) -> Unit) {
        imageDetailCard.setOnTagClickListener {
            callback(it)
        }
    }
}