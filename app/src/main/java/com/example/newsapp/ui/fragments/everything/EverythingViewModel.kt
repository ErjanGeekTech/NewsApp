package com.example.newsapp.ui.fragments.everything

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.data.repositories.EverythingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(private val repository: EverythingRepository) :
    BaseViewModel() {
    fun fetchEverything() = repository.fetchEverything().cachedIn(viewModelScope)
}