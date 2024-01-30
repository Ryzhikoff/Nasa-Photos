package evgeniy.ryzhikov.nasaphotos.ui.home

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import evgeniy.ryzhikov.apod_module.api.ApodResultDtoList
import evgeniy.ryzhikov.nasaphotos.R
import evgeniy.ryzhikov.nasaphotos.databinding.FragmentHomeBinding
import evgeniy.ryzhikov.nasaphotos.models.RandomPhotoModel
import evgeniy.ryzhikov.nasaphotos.models.toRandomPhotoModel
import evgeniy.ryzhikov.nasaphotos.utils.GlideListener
import evgeniy.ryzhikov.utils.models.doOnError
import evgeniy.ryzhikov.utils.models.doOnSuccess

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        viewModel.getRandomPhoto()
        setClickListeners()
        setViewModelListener()
    }

    private fun setViewModelListener() {
        viewModel.randomPhoto.observe(viewLifecycleOwner) { result ->
            result.doOnSuccess {
                val apodResult = (it as ApodResultDtoList)[0]
                setContent(apodResult.toRandomPhotoModel())
            }
            result.doOnError { errorBody ->
                printError(errorBody)
            }
            binding.update.isEnabled = true
        }
    }

    private fun setContent(model: RandomPhotoModel) {
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
            .error(R.drawable.ic_not_load)
            .listener(GlideListener.OnCompleted{
                showProgressBar(false)
            })
            .transform(CenterCrop(), RoundedCorners(20.toPx))
            .into(binding.randomImage)
    }

    private fun printError(errorBody: String?) {
        Toast.makeText(requireContext(), getString(R.string.error_load, errorBody), Toast.LENGTH_SHORT).show()
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