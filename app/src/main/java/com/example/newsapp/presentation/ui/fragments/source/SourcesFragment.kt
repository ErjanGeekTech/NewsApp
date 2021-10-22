package com.example.newsapp.presentation.ui.fragments.source

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSourcesBinding
import com.example.newsapp.domain.models.Sources
import com.example.newsapp.extensions.scrollListenerUploadNextPage
import com.example.newsapp.presentation.state.UIState
import com.example.newsapp.presentation.ui.activity.MainActivity
import com.example.newsapp.presentation.ui.adapters.SourcesCountryUsAdapter
import com.example.pokemons.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourcesFragment : BaseFragment<FragmentSourcesBinding, SourcesViewModel>(
    R.layout.fragment_sources
) {

    override val binding by viewBinding(FragmentSourcesBinding::bind)
    override val viewModel: SourcesViewModel by viewModels()
    private val sourcesAdapter = SourcesCountryUsAdapter()

    override fun setupViews() {
        setupRecycler()
    }

    override fun setupListeners() {
        bottomNavigationItemReselectListener()
        onScrollListener()
    }

    override fun setupObserves() {
        subscribeToSources()
    }

    private fun setupRecycler() = with(binding.rv) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = sourcesAdapter
    }

    private fun onScrollListener() {
        binding.rv.scrollListenerUploadNextPage(viewModel)
    }

    private fun bottomNavigationItemReselectListener() {
        (requireActivity() as MainActivity).setOnBottomNavigationItemReselectListener {
            binding.rv.smoothScrollToPosition(0)
        }
    }

    private fun subscribeToSources() {
        viewModel.sourcesState.observe(viewLifecycleOwner, {
            when (it) {
                is UIState.Loading -> {
                    binding.swipeSourcesCountry.isRefreshing = true
                }
                is UIState.Error -> {
                    Log.e("anime", it.error)
                }
                is UIState.Success -> {
                    binding.swipeSourcesCountry.isRefreshing = false
                    val dataList = ArrayList<Sources>(sourcesAdapter.currentList)
                    it.data?.let { data -> dataList.addAll(data) }
                    sourcesAdapter.submitList(dataList)
                }
            }
        })
    }
}