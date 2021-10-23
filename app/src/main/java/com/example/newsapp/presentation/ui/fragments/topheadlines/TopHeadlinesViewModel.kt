package com.example.newsapp.presentation.ui.fragments.topheadlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.base.BaseFetchRequest
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.domain.models.EverythingModel
import com.example.newsapp.domain.usecases.TopHeadlinesUseCases
import com.example.newsapp.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesViewModel @Inject constructor(private val useCases: TopHeadlinesUseCases) :
    BaseViewModel(), BaseFetchRequest {

    private val _topHeadlinesState = MutableLiveData<UIState<List<EverythingModel>?>>()
    val topHeadlinesState: LiveData<UIState<List<EverythingModel>?>> = _topHeadlinesState
    override var page: Int = 1

    init {
        fetchNews(1)
    }

    override fun fetchNews(page: Int) {
        subscribeTo(_topHeadlinesState) {
            useCases.invoke(page)
        }
    }
}