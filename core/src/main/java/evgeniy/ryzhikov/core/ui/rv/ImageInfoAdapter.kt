package evgeniy.ryzhikov.core.ui.rv

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.core.ui.customview.ImageDetailCard
import evgeniy.ryzhikov.core.ui.rv.PaginationParams.MAX_ITEMS_SIZE

class ImageInfoAdapter(
    private val onItemClickListener: OnItemClickListener? = null,
    private val onFavoriteClickListener: OnFavoriteClickListener? = null,
    private val onTagClickListener: OnTagClickListener? = null,
) : RecyclerView.Adapter<ImageInfoViewHolder>() {

    private var items: MutableList<ImageInfoUi> = mutableListOf()
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
            randomPhotoUi.isFavorite = it
            onFavoriteClickListener?.onClick(randomPhotoUi)
        }

        holder.setOnItemClickListener {
            onItemClickListener?.onClick(randomPhotoUi)
        }

        holder.setOnTagClickListener { tagName ->
            onTagClickListener?.onClick(tagName)
        }
    }

    override fun getItemCount(): Int =
        items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<ImageInfoUi>) {
        items = newList.toMutableList()

        notifyDataSetChanged()
    }

    fun addItems(newList: List<ImageInfoUi>, addInEnd: Boolean) {
        if (addInEnd) {
            addItemsInEnd(newList)
        } else {
            addItemsInStart(newList)
        }
    }

    private fun addItemsInEnd(newList: List<ImageInfoUi>) {
        newList.forEach { imageInfoUi ->
            val position = items.size
            items.add(position, imageInfoUi)
            notifyItemInserted(position)
        }
        if (items.size > MAX_ITEMS_SIZE) {
            val diff = items.size - MAX_ITEMS_SIZE

            val startIndex = 0
            val endIndex = startIndex + diff

            println("addItemsInEnd")
            removeItemsRange(startIndex, endIndex)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addItemsInStart(newItems: List<ImageInfoUi>) {
//        newItems.forEach { imageInfoUi ->
//            items.add(0, imageInfoUi)
////            notifyItemInserted(0)
//        }
//        notifyDataSetChanged()
//        if (items.size > MAX_ITEMS_SIZE) {
//            val diff = items.size - MAX_ITEMS_SIZE
//
//            val endIndex = items.size - 1
//            val startIndex = endIndex - diff
//
//            println("addItemsInStart")
//            removeItemsRange(startIndex, endIndex)
//        }

        val oldItems = items
        newItems.forEach { imageInfoUi ->
            items.add(0, imageInfoUi)
        }

        println("addItemsInStart items.size before ${items.size}")
        if (items.size > MAX_ITEMS_SIZE) {
            val diffSize = items.size - MAX_ITEMS_SIZE

            val endIndex = items.size - 1
            val startIndex = endIndex - diffSize
            val delItems = items.subList(startIndex, endIndex)
            items.removeAll(delItems)
        }

        val diff = ImageInfoDiffUtil(oldItems, items)
        val diffResult = DiffUtil.calculateDiff(diff)
        diffResult.dispatchUpdatesTo(this)

        println("addItemsInStart items.size after ${items.size}")
//        val diff = ProductDiff(items.orEmpty(), newList)
//        val diffResult = DiffUtil.calculateDiff(diff)
//        println("after ${items?.size}")
//        diffResult.dispatchUpdatesTo(this)
    }

    private fun removeItemsRange(startIndex: Int, endIndex: Int) {

        println("items.size before remove ${items.size}")
        val delItems = items.subList(startIndex, endIndex)
        items.removeAll(delItems)
        notifyItemRangeRemoved(startIndex, endIndex)
        println("items.size after remove ${items.size}")
    }

    fun removeItem(randomPhotoUi: ImageInfoUi) {
        removedItemPosition = items.indexOf(randomPhotoUi)
        items.remove(randomPhotoUi)
        notifyItemRemoved(removedItemPosition)
    }

    fun addItem(randomPhotoUi: ImageInfoUi) {
        items.add(removedItemPosition, randomPhotoUi)
        notifyItemInserted(removedItemPosition)
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

    class ImageInfoDiffUtil(
        private val oldList: List<ImageInfoUi>,
        private val newList: List<ImageInfoUi>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].imageUrl == newList[newItemPosition].imageUrl

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]


    }
}