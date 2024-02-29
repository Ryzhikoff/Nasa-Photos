package evgeniy.ryzhikov.core.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.view.forEach
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import evgeniy.ryzhikov.core.R
import evgeniy.ryzhikov.core.databinding.ImageDetailCardBinding
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.core.utils.toPx

class ImageDetailCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding: ImageDetailCardBinding
    private var randomPhotoUi: ImageInfoUi? = null

    init {
        binding = ImageDetailCardBinding.bind(inflate(context, R.layout.image_detail_card, this))
        background = null
    }

    fun setOnFavoritesClickListener(callbacks: (Boolean) -> Unit) = with(binding) {
        favorites.setOnClickListener {
            favorites.isSelected = !favorites.isSelected
            callbacks(favorites.isSelected)
        }
    }

    fun setData(randomPhotoUi: ImageInfoUi) = with(binding) {
        this@ImageDetailCard.randomPhotoUi = randomPhotoUi
        title.text = randomPhotoUi.title
        date.text = randomPhotoUi.date
        favorites.isSelected = randomPhotoUi.isFavorite

        tagCotainer.removeAllViews()
        if (randomPhotoUi.tags == null) {
            tagCotainer.visibility = View.INVISIBLE
        } else {
            randomPhotoUi.tags.forEach { tagName ->
                tagCotainer.addView(TagView(context, tagName))
            }
        }

        Glide.with(this@ImageDetailCard)
            .load(randomPhotoUi.imageUrl)
            .error(R.drawable.ic_not_load)
            .transform(CenterCrop(), RoundedCorners(20.toPx))
            .into(image)
    }

    fun setOnTagClickListener(callback: (String) -> Unit) {
        binding.tagCotainer.forEach { tageView ->
            tageView.findViewById<AppCompatButton>(R.id.name).setOnClickListener {
                tageView as TagView
                callback(tageView.tagName)
            }
        }
    }
}