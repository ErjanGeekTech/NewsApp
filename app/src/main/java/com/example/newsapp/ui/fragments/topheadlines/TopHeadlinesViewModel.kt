package com.example.newsapp.ui.fragments.topheadlines

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.data.repositories.TopHeadlinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel @Inject constructor(private val repository: TopHeadlinesRepository) :
    BaseViewModel() {

    fun fetchTopHeadlines() = repository.fetchTopHeadlines().cachedIn(viewModelScope)
}