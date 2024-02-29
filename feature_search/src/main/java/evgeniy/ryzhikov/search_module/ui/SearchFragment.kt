package evgeniy.ryzhikov.search_module.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import evgeniy.ryzhikov.core.models.ImageInfoUi
import evgeniy.ryzhikov.core.ui.rv.ImageInfoAdapter
import evgeniy.ryzhikov.feature_search.R
import evgeniy.ryzhikov.feature_search.databinding.FragmentSearchBinding
import evgeniy.ryzhikov.remote.models.doOnError
import evgeniy.ryzhikov.remote.models.doOnSuccess
import evgeniy.ryzhikov.search_module.di.modules.SearchComponentProvider
import evgeniy.ryzhikov.search_module.utils.SearchViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val onTagClickListener = object : ImageInfoAdapter.OnTagClickListener {
        override fun onClick(tagName: String) {
            binding.searchEditText.setText(tagName)
            startSearch(tagName)
        }

    }

    private val onFavoriteClickListener = object : ImageInfoAdapter.OnFavoriteClickListener {
        override fun onClick(imageInfoUi: ImageInfoUi) {
            viewModel.toFavorite(isAdd = imageInfoUi.isFavorite, imageInfoUi = imageInfoUi)
        }

    }
    private val adapter: ImageInfoAdapter = ImageInfoAdapter(
        onTagClickListener = onTagClickListener,
        onFavoriteClickListener = onFavoriteClickListener,
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        (requireActivity().application as SearchComponentProvider)
            .getSearchComponent()
            .inject(this)

        setSearchListener()
        resultListener()
        initRV()
    }

    private fun initRV() {
        binding.recyclerView.apply {
            adapter = this@SearchFragment.adapter
            initSearchPagination()
        }
    }

    private fun RecyclerView.initSearchPagination() {
        //Добавляем слушатель для скролла нашего RV
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //Если по оси Y есть изменение
                //Получаем количество видимых элементов
                val visibleItemCount = recyclerView.layoutManager!!.childCount
                //Получаем количесвто общих элементов
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                //Находим первый видиимый элемент при скролле
                val firstVisibleItemCount =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                //Совсем этим вызываем метод для пагинации
                viewModel.doSearchPagination(
                    visibleItemCount,
                    totalItemCount,
                    firstVisibleItemCount,
                    binding.searchEditText.text.toString(),
                    dy >= 0
                )
            }
        })
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
        binding.progressBar.isVisible = true
        adapter.submitList(emptyList())
        viewModel.search(query)
    }

    private fun resultListener() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchResult.collect { apiResult ->
                apiResult.doOnSuccess {
                    try {
                        @Suppress("UNCHECKED_CAST")
                        onSuccess(it as List<ImageInfoUi>)
                    } catch (e: Exception) {
                        onError(e.toString())
                    }
                }
                apiResult.doOnError {
                    onError(it)
                }
                binding.progressBar.isVisible = false
                viewModel.isLoading = false
            }
        }
    }

    private fun onSuccess(imageInfoUis: List<ImageInfoUi>) {
        if (imageInfoUis.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.not_found_by_query, binding.searchEditText.text), Toast.LENGTH_SHORT).show()
            return
        }
        adapter.addItems(newList = imageInfoUis, addInEnd = viewModel.addPageIsNext)
    }

    private fun onError(errorBody: String?) {
        Toast.makeText(requireContext(), "${getString(R.string.search_error)} $errorBody", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}