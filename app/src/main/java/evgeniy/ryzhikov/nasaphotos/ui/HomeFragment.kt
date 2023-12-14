package evgeniy.ryzhikov.nasaphotos.ui

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import evgeniy.ryzhikov.nasaphotos.R
import evgeniy.ryzhikov.nasaphotos.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        Glide.with(this)
            .load("https://apod.nasa.gov/apod/image/0701/keplersnr_chandra.jpg")
            .transform(CenterCrop(), RoundedCorners(20.toPx))
            .into(binding.randomImage)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

val Int.toPx get() = (this * Resources.getSystem().displayMetrics.density).toInt()