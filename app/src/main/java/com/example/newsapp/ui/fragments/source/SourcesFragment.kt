package com.example.newsapp.ui.fragments.source

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSourcesBinding
import com.example.newsapp.ui.adapters.SourcesCountryUsAdapter
import com.example.pokemons.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SourcesFragment : BaseFragment<FragmentSourcesBinding, SourcesViewModel>(
    R.layout.fragment_sources
) {
    override val binding by viewBinding(FragmentSourcesBinding::bind)
    override val viewModel: SourcesViewModel by viewModels()

    private val everythingAdapter = SourcesCountryUsAdapter()

    override fun initialize() {
        viewModel.fetchSourcesCountryUs()
    }

    override fun setupRequests() {
        fetchSourcesCountryUs()
    }

    override fun setupViews() {
        setupRecycler()
    }

    override fun setupListeners() {
        loadStateListener()
        listenerSwipe()
    }

    private fun loadStateListener() {
        everythingAdapter.addLoadStateListener {
            binding.swipeSourcesCountry.isRefreshing = it.refresh == LoadState.Loading
        }
    }

    private fun listenerSwipe() {
        binding.swipeSourcesCountry.setOnRefreshListener {
            everythingAdapter.refresh()
        }
    }

    private fun setupRecycler() = with(binding.rv) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = everythingAdapter
    }

    private fun fetchSourcesCountryUs() {
        lifecycleScope.launch {
            viewModel.fetchSourcesCountryUs().collectLatest {
                everythingAdapter.submitData(it)
            }
        }
    }
}