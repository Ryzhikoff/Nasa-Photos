package evgeniy.ryzhikov.search_module.ui.rv

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.core.ui.customview.ImageDetailCard

class ImageInfoPagingAdapter(
    private val onItemClickListener: OnItemClickListener? = null,
    private val onFavoriteClickListener: OnFavoriteClickListener? = null,
    private val onTagClickListener: OnTagClickListener? = null,
) : PagingDataAdapter<ImageInfoUi, ImageInfoViewHolder>(ImageInfoDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageInfoViewHolder {
        val imageDetailCard = ImageDetailCard(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return ImageInfoViewHolder(imageDetailCard)
    }

    override fun onBindViewHolder(holder: ImageInfoViewHolder, position: Int) {
        getItem(position)?.let { imageInfoUi ->
            holder.init(imageInfoUi)
            holder.setOnFavoriteClickListener {
                imageInfoUi.isFavorite = it
                onFavoriteClickListener?.onClick(imageInfoUi)
            }

            holder.setOnItemClickListener {
                onItemClickListener?.onClick(imageInfoUi)
            }

            holder.setOnTagClickListener { tagName ->
                onTagClickListener?.onClick(tagName)
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(imageInfoUi: ImageInfoUi)
    }

    interface OnFavoriteClickListener {
        fun onClick(imageInfoUi: ImageInfoUi)
    }

    interface OnTagClickListener {
        fun onClick(tagName: String)
    }

    private object ImageInfoDiffItemCallback : DiffUtil.ItemCallback<ImageInfoUi>() {

        override fun areItemsTheSame(oldItem: ImageInfoUi, newItem: ImageInfoUi): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ImageInfoUi, newItem: ImageInfoUi): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }
    }
}