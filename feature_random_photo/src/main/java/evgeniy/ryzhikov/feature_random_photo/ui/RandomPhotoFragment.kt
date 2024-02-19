package evgeniy.ryzhikov.feature_random_photo.ui

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import evgeniy.ryzhikov.core.utils.GlideListener
import evgeniy.ryzhikov.feature_random_photo.R
import evgeniy.ryzhikov.feature_random_photo.databinding.FragmentRandomPhotoBinding
import evgeniy.ryzhikov.feature_random_photo.di.modules.RandomPhotoComponentProvider
import evgeniy.ryzhikov.feature_random_photo.models.RandomPhotoUi
import evgeniy.ryzhikov.feature_random_photo.utils.RandomPhotoViewModelFactory
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
                    setContent(it as RandomPhotoUi)
                }

                result.doOnError {errorBody ->
                    printError(errorBody)
                }

                binding.update.isEnabled = true
            }
        }
    }

    private fun setContent(model: RandomPhotoUi) {
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
            .listener(GlideListener.OnCompleted{
                showProgressBar(false)
            })
            .transform(CenterInside(), RoundedCorners(20.toPx))
            .into(binding.randomImage)
    }

    private fun printError(errorBody: String?) {
        Toast.makeText(requireContext(), getString(evgeniy.ryzhikov.core.R.string.error_load, errorBody), Toast.LENGTH_SHORT).show()
    }

    private fun setClickListeners() {
        binding.update.setOnClickListener {
            viewModel.getRandomPhoto()
            it.isEnabled = false
            showProgressBar(true)
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


val Int.toPx get() = (this * Resources.getSystem().displayMetrics.density).toInt()