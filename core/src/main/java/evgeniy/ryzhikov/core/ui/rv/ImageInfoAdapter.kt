package evgeniy.ryzhikov.core.ui.rv

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import evgeniy.ryzhikov.core.models.RandomPhotoUi
import evgeniy.ryzhikov.core.ui.customview.ImageDetailCard

class ImageInfoAdapter (
    private val onItemClickListener: OnItemClickListener? = null,
    private val onFavoriteClickListener: OnFavoriteClickListener? = null,
) : RecyclerView.Adapter<ImageInfoViewHolder>() {

    private var items: MutableList<RandomPhotoUi> = mutableListOf()
    private var removedItemPosition = 0
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
        val randomPhotoUi = items[position]
        holder.init(randomPhotoUi)
        holder.setOnFavoriteClickListener {
            onFavoriteClickListener?.onClick(randomPhotoUi)
        }

        holder.setOnItemClickListener {
            onItemClickListener?.onClick(randomPhotoUi)
        }
    }

    override fun getItemCount(): Int =
        items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<RandomPhotoUi>) {
        items = newList.toMutableList()
        notifyDataSetChanged()
    }

    fun removeItem(randomPhotoUi: RandomPhotoUi) {
        removedItemPosition = items.indexOf(randomPhotoUi)
        items.remove(randomPhotoUi)
        notifyItemRemoved(removedItemPosition)
    }

    fun addItem(randomPhotoUi: RandomPhotoUi) {
        items.add(removedItemPosition, randomPhotoUi)
        notifyItemInserted(removedItemPosition)
    }

    interface OnItemClickListener {
        fun onClick(randomPhotoUi: RandomPhotoUi)
    }

    interface OnFavoriteClickListener {
        fun onClick(randomPhotoUi: RandomPhotoUi)
    }
}