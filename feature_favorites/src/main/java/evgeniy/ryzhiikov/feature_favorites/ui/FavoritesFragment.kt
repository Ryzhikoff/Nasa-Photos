package evgeniy.ryzhiikov.feature_favorites.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.feature_favorites.R
import com.example.feature_favorites.databinding.FragmentFavoritesBinding
import com.google.android.material.snackbar.Snackbar
import evgeniy.ryzhiikov.feature_favorites.di.modules.FavoritesComponentProvider
import evgeniy.ryzhiikov.feature_favorites.utils.FavoritesViewModelFactory
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.core.ui.rv.ImageInfoAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: FavoritesViewModelFactory

    private val viewModel: FavoritesViewModel by viewModels { viewModelFactory }

    private val onFavoriteClickListener = object : ImageInfoAdapter.OnFavoriteClickListener {
        override fun onClick(imageInfoUi: ImageInfoUi) {
            onFavoriteClick(imageInfoUi)
        }
    }

    private val onItemClickListener = object : ImageInfoAdapter.OnItemClickListener {
        override fun onClick(imageInfoUi: ImageInfoUi) {
            println("onItemClickListener $imageInfoUi")
        }

    }

    private var adapter: ImageInfoAdapter = ImageInfoAdapter(onItemClickListener, onFavoriteClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)
        (requireActivity().application as FavoritesComponentProvider)
            .getFavoritesComponent()
            .inject(this)

        viewModel.getFavoritesList()
        resultListener()
        initRV()
    }

    private fun initRV() {
        binding.recyclerView.adapter = adapter
    }

    private fun resultListener() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoriteList.collect {
                adapter.submitList(it)
            }
        }
    }

    private fun onFavoriteClick(randomPhotoUi: ImageInfoUi) {
        viewModel.deleteFromFavorites(randomPhotoUi)
        adapter.removeItem(randomPhotoUi)
        makeSnakeBar(randomPhotoUi)
    }

    private fun makeSnakeBar(randomPhotoUi: ImageInfoUi) {
        Snackbar.make(binding.recyclerView, getString(R.string.remove_from_favorites), LENGTH_SNACKBAR).apply {
            setAction(getString(R.string.cancel)) {
                viewModel.addToFavorite(randomPhotoUi)
                adapter.addItem(randomPhotoUi)
            }
            show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        const val LENGTH_SNACKBAR = 1500
    }
}