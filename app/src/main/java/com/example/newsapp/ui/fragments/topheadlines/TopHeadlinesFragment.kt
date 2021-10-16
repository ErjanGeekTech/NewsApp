package com.example.newsapp.ui.fragments.topheadlines

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentTopHeadlinesBinding
import com.example.newsapp.ui.adapters.EverythingAdapter
import com.example.pokemons.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopHeadlinesFragment : BaseFragment<FragmentTopHeadlinesBinding, TopHeadlinesViewModel>(
    R.layout.fragment_top_headlines
) {
    override val binding by viewBinding(FragmentTopHeadlinesBinding::bind)
    override val viewModel: TopHeadlinesViewModel by viewModels()

    private val everythingAdapter = EverythingAdapter()

    override fun setupRequests() {
        fetchTopHeadlines()
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
            try {
                binding.swipeHeadlines.isRefreshing = it.refresh == LoadState.Loading
            } catch (e: IllegalStateException) {
                Log.e("anime", "$e")
            }
        }
    }

    private fun listenerSwipe() {
        binding.swipeHeadlines.setOnRefreshListener {
            everythingAdapter.refresh()
        }
    }

    private fun setupRecycler() = with(binding.rv) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = everythingAdapter
    }

    private fun fetchTopHeadlines() {
        lifecycleScope.launch {
            viewModel.fetchTopHeadlines().collectLatest {
                everythingAdapter.submitData(it)
            }
        }
    }
}