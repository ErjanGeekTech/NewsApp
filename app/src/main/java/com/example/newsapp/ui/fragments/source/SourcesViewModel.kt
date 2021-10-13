package com.example.newsapp.ui.fragments.source

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.data.repositories.SourcesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(private val repository: SourcesRepository) :
    BaseViewModel() {

    fun fetchSourcesCountryUs() = repository.fetchSourceCountryUs().cachedIn(viewModelScope)
}