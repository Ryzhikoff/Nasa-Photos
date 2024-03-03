package evgeniy.ryzhikov.search_module.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.feature_search.R
import evgeniy.ryzhikov.feature_search.databinding.FragmentSearchBinding
import evgeniy.ryzhikov.features_details.ui.DetailsFragment
import evgeniy.ryzhikov.search_module.di.modules.SearchComponentProvider
import evgeniy.ryzhikov.search_module.ui.rv.ImageInfoPagingAdapter
import evgeniy.ryzhikov.search_module.ui.rv.ImageLoaderStateAdapter
import evgeniy.ryzhikov.search_module.utils.SearchViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val onItemClickListener = object : ImageInfoPagingAdapter.OnItemClickListener {
        override fun onClick(imageInfoUi: ImageInfoUi) {
            navigateToDetails(imageInfoUi)
        }

    }

    private val onTagClickListener = object : ImageInfoPagingAdapter.OnTagClickListener {
        override fun onClick(tagName: String) {
            binding.searchEditText.setText(tagName)
            startSearch(tagName)
        }

    }

    private val onFavoriteClickListener = object : ImageInfoPagingAdapter.OnFavoriteClickListener {
        override fun onClick(imageInfoUi: ImageInfoUi) {
            viewModel.toFavorite(isAdd = imageInfoUi.isFavorite, imageInfoUi = imageInfoUi)
        }

    }

    private val pagingAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ImageInfoPagingAdapter(
            onTagClickListener = onTagClickListener,
            onFavoriteClickListener = onFavoriteClickListener,
            onItemClickListener = onItemClickListener,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        (requireActivity().application as SearchComponentProvider)
            .getSearchComponent()
            .inject(this)

        binding.recyclerView.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
            header = ImageLoaderStateAdapter(),
            footer = ImageLoaderStateAdapter()
        )

        pagingAdapter.addLoadStateListener { state ->
            with(binding) {
                recyclerView.isVisible = state.refresh != LoadState.Loading
                progressBar.isVisible = state.refresh == LoadState.Loading
            }

            if (state.append.endOfPaginationReached) {
                if (pagingAdapter.itemCount < 1) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.not_found_by_query, binding.searchEditText.text.toString()),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchResult.collectLatest { pagingData ->
                pagingAdapter.submitData(lifecycle, pagingData)
            }

            viewModel.query
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .onEach(::updateSearchQuery)
                .launchIn(lifecycleScope)
        }
        setSearchListener()

    }

    private fun updateSearchQuery(searchQuery: String) {
        with(binding.searchEditText) {
            if ((text?.toString() ?: "") != searchQuery) {
                setText(searchQuery)
            }
        }
    }

    private fun setSearchListener() {
        binding.searchEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                startSearch(binding.searchEditText.text.toString())
            }
            return@setOnEditorActionListener false
        }
    }

    private fun startSearch(query: String) {
        pagingAdapter.submitData(lifecycle, PagingData.empty())
        viewModel.setQuery(query)
    }

    private fun navigateToDetails(imageInfoUi: ImageInfoUi) {
        val extras = bundleOf(Pair(DetailsFragment.KEY_DETAILS_ITEM, imageInfoUi))
        findNavController().navigate(R.id.action_searchFragment_to_detailsFragment, extras)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}