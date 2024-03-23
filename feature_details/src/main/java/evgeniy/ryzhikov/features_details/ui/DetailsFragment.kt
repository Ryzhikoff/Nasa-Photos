package evgeniy.ryzhikov.features_details.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import evgeniy.ryzhikov.core.extensions.getParcelableCompat
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.core.utils.GlideListener
import evgeniy.ryzhikov.core.utils.toPx
import evgeniy.ryzhikov.features_details.R
import evgeniy.ryzhikov.features_details.databinding.FragmentDetailsBinding
import evgeniy.ryzhikov.features_details.di.DetailsComponentProvider
import javax.inject.Inject
import javax.inject.Provider

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    private var imageInfoUi: ImageInfoUi? = null

    @Inject
    lateinit var viewModelProvider: Provider<DetailsViewModel.Factory>

    private val viewModel: DetailsViewModel by viewModels { viewModelProvider.get() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)
        (requireActivity().application as DetailsComponentProvider)
            .getDetailComponent()
            .inject(this)


        showProgressBar(true)
        arguments?.getParcelableCompat<ImageInfoUi>(KEY_DETAILS_ITEM)?.let {
            imageInfoUi = it
            setContent(it)
        }

        setClickListeners()
    }

    private fun setClickListeners() = with(binding) {
        share.setOnClickListener {
            onShareClick()
        }

        favorites.setOnClickListener {
            onFavoritesClick()
        }
    }

    private fun onFavoritesClick() {
        imageInfoUi?.let {
            it.isFavorite = !it.isFavorite
            binding.favorites.isSelected = !binding.favorites.isSelected
            viewModel.toFavorite(isAdd = it.isFavorite, imageInfoUi = it)
        }
    }

    private fun onShareClick() {

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "${imageInfoUi?.title}:\n" +
                        "${imageInfoUi?.imageUrl}"
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun setContent(imageInfoUi: ImageInfoUi) = with(binding) {
        titleToolbar.title = imageInfoUi.title
        titleToolbar.subtitle = imageInfoUi.date
        collapsingToolBar.setExpandedTitleColor(ContextCompat.getColor(requireContext(), evgeniy.ryzhikov.core.R.color.secondary))
        description.text = imageInfoUi.description
        favorites.isSelected = imageInfoUi.isFavorite

        Glide.with(this@DetailsFragment)
            .load(imageInfoUi.imageUrl)
            .error(evgeniy.ryzhikov.core.R.drawable.ic_not_load)
            .listener(GlideListener.OnCompleted {
                showProgressBar(false)
            })
            .transform(CenterCrop(), RoundedCorners(20.toPx))
            .into(image)
    }

    private fun showProgressBar(value: Boolean) {
        binding.progressBar.isVisible = value
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_DETAILS_ITEM = "key_details_item"
    }
}