package evgeniy.ryzhikov.core.ui.rv

import androidx.recyclerview.widget.RecyclerView
import evgeniy.ryzhikov.core.models.RandomPhotoUi
import evgeniy.ryzhikov.core.ui.customview.ImageDetailCard

class ImageInfoViewHolder(
    private val imageDetailCard: ImageDetailCard,
): RecyclerView.ViewHolder(imageDetailCard) {

    fun init (randomPhotoUi: RandomPhotoUi) {
        imageDetailCard.setData(randomPhotoUi)

    }

    fun setOnFavoriteClickListener(callback: () -> Unit) {
        imageDetailCard.setOnFavoritesClickListener {
            callback()
        }
    }

    fun setOnItemClickListener(callback: () -> Unit) {
        imageDetailCard.setOnClickListener {
            callback()
        }
    }
}