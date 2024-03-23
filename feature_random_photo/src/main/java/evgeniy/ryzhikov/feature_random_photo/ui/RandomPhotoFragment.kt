package evgeniy.ryzhikov.feature_random_photo.ui

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.core.utils.GlideListener
import evgeniy.ryzhikov.core.utils.toPx
import evgeniy.ryzhikov.feature_random_photo.R
import evgeniy.ryzhikov.feature_random_photo.databinding.FragmentRandomPhotoBinding
import evgeniy.ryzhikov.feature_random_photo.di.RandomPhotoComponentProvider
import evgeniy.ryzhikov.feature_random_photo.utils.RandomPhotoViewModelFactory
import evgeniy.ryzhikov.features_details.ui.DetailsFragment.Companion.KEY_DETAILS_ITEM
import evgeniy.ryzhikov.remote.models.doOnError
import evgeniy.ryzhikov.remote.models.doOnSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomPhotoFragment : Fragment(R.layout.fragment_random_photo) {
    private var _binding: FragmentRandomPhotoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: RandomPhotoViewModelFactory

    private val viewModel: RandomPhotoViewModel by viewModels { viewModelFactory }
    private var imageInfoUi: ImageInfoUi? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (requireActivity().application as RandomPhotoComponentProvider)
            .getRandomPhotoComponent()
            .inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRandomPhotoBinding.bind(view)

        viewModel.getRandomPhoto()
        setClickListeners()
        setResultListener()
    }

    private fun setResultListener() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.randomPhoto.collect { result ->
                result.doOnSuccess {
                    setContent(it as ImageInfoUi)
                }

                result.doOnError { errorBody ->
                    printError(errorBody)
                }

                binding.update.isEnabled = true
            }
        }
    }

    private fun setContent(model: ImageInfoUi) {
        val s = SpannableString("")
        imageInfoUi = model
        setImage(model.imageUrl)
        with(binding) {
            title.text = model.title
            date.text = model.date
            description.text = model.description
        }
    }

    private fun setImage(url: String?) {
        Glide.with(this)
            .load(url)
            .error(evgeniy.ryzhikov.core.R.drawable.ic_not_load)
            .listener(GlideListener.OnCompleted {
                showProgressBar(false)
            })
            .transform(CenterCrop(), RoundedCorners(20.toPx))
            .into(binding.randomImage)
    }

    private fun printError(errorBody: String?) {
        Toast.makeText(requireContext(), getString(evgeniy.ryzhikov.core.R.string.error_load, errorBody), Toast.LENGTH_SHORT).show()
    }

    private fun setClickListeners() = with(binding) {
        update.setOnClickListener {
            onUpdatePhotoClick(it)
        }
        favoritesButton.setOnClickListener {
            onFavoriteButtonClick()
        }

        randomImage.setOnClickListener {
            navigateToDetails()
        }
    }

    private fun navigateToDetails() {
        imageInfoUi?.let {
            val extras = bundleOf(Pair(KEY_DETAILS_ITEM, imageInfoUi))
            findNavController().navigate(R.id.action_randomPhotoFragment_to_detailsFragment, extras)
        }
    }

    private fun onUpdatePhotoClick(it: View) {
        it.isEnabled = false
        showProgressBar(true)
        binding.favoritesButton.isSelected = false
        viewModel.getRandomPhoto()
    }

    private fun onFavoriteButtonClick() = with(binding) {
        imageInfoUi?.let {
            favoritesButton.isSelected = !favoritesButton.isSelected
            viewModel.toFavorite(isAdd = favoritesButton.isSelected, randomPhotoUi = it)
        }
    }

    private fun showProgressBar(value: Boolean) {
        binding.progressBar.isVisible = value
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
