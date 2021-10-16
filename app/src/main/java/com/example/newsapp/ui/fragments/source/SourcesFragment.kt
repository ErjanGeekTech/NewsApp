package com.example.newsapp.ui.fragments.source

import android.util.Log
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

    private val sourcesAdapter = SourcesCountryUsAdapter()

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
        sourcesAdapter.addLoadStateListener {
            try {
                binding.swipeSourcesCountry.isRefreshing = it.refresh == LoadState.Loading
            } catch (e: IllegalStateException) {
                Log.e("anime", "$e")
            }
        }
    }

    private fun listenerSwipe() {
        binding.swipeSourcesCountry.setOnRefreshListener {
            sourcesAdapter.refresh()
        }
    }

    private fun setupRecycler() = with(binding.rv) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = sourcesAdapter
    }

    private fun fetchSourcesCountryUs() {
        lifecycleScope.launch {
            viewModel.fetchSourcesCountryUs().collectLatest {
                sourcesAdapter.submitData(it)
            }
        }
    }
}