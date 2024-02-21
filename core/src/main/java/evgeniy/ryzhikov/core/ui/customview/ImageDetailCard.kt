package evgeniy.ryzhikov.core.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import evgeniy.ryzhikov.core.R
import evgeniy.ryzhikov.core.databinding.ImageDetailCardBinding
import evgeniy.ryzhikov.core.models.RandomPhotoUi
import evgeniy.ryzhikov.core.utils.toPx

class ImageDetailCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding: ImageDetailCardBinding
    private var randomPhotoUi: RandomPhotoUi? = null

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

    fun setData(randomPhotoUi: RandomPhotoUi) = with(binding) {
        this@ImageDetailCard.randomPhotoUi = randomPhotoUi
        title.text = randomPhotoUi.title
        date.text = randomPhotoUi.date
        favorites.isSelected = randomPhotoUi.isFavorite

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
}