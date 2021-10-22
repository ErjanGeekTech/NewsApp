package com.example.newsapp.presentation.ui.fragments.everything

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentEverythingBinding
import com.example.newsapp.domain.models.EverythingModel
import com.example.newsapp.extensions.scrollListenerUploadNextPage
import com.example.newsapp.presentation.state.UIState
import com.example.newsapp.presentation.ui.activity.MainActivity
import com.example.newsapp.presentation.ui.adapters.ArticleAdapter
import com.example.pokemons.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EverythingFragment : BaseFragment<FragmentEverythingBinding, EverythingViewModel>(
    R.layout.fragment_everything
) {

    override val binding by viewBinding(FragmentEverythingBinding::bind)
    override val viewModel: EverythingViewModel by viewModels()
    private val everythingAdapter = ArticleAdapter()

    override fun setupViews() {
        setupRecycler()
    }

    override fun setupListeners() {
        bottomNavigationItemReselectListener()
        onScrollListener()
    }

    override fun setupObserves() {
        subscribeToEverything()
    }

    private fun setupRecycler() = with(binding.rv) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = everythingAdapter
    }

    private fun onScrollListener() {
        binding.rv.scrollListenerUploadNextPage(viewModel)
    }

    private fun bottomNavigationItemReselectListener() {
        (requireActivity() as MainActivity).setOnBottomNavigationItemReselectListener {
            binding.rv.smoothScrollToPosition(0)
        }
    }

    private fun subscribeToEverything() {
        viewModel.everythingState.observe(viewLifecycleOwner, {
            when (it) {
                is UIState.Loading -> {
                    binding.swipeEverything.isRefreshing = true
                }
                is UIState.Error -> {
                    Log.e("anime", it.error)
                }
                is UIState.Success -> {
                    binding.swipeEverything.isRefreshing = false
                    val dataList = ArrayList<EverythingModel>(everythingAdapter.currentList)
                    it.data?.let { data -> dataList.addAll(data) }
                    everythingAdapter.submitList(dataList)
                }
            }
        })
    }
}