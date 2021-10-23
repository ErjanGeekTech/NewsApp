package com.example.newsapp.presentation.ui.fragments.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.base.BaseFetchRequest
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.domain.models.Sources
import com.example.newsapp.domain.usecases.SourcesUseCases
import com.example.newsapp.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(private val useCases: SourcesUseCases) :
    BaseViewModel(), BaseFetchRequest {

    private val _sourcesState = MutableLiveData<UIState<List<Sources>?>>()
    val sourcesState: LiveData<UIState<List<Sources>?>> = _sourcesState
    override var page: Int = 1

    init {
        fetchNews(1)
    }

    override fun fetchNews(page: Int) {
        subscribeTo(_sourcesState) {
            useCases.invoke(page)
        }
    }
}