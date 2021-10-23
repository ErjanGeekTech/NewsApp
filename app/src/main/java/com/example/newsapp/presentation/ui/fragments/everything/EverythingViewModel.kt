package com.example.newsapp.presentation.ui.fragments.everything

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.base.BaseFetchRequest
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.domain.models.EverythingModel
import com.example.newsapp.domain.usecases.EverythingUseCases
import com.example.newsapp.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(
    private val useCases: EverythingUseCases
) : BaseViewModel(), BaseFetchRequest {

    private val _everythingState = MutableLiveData<UIState<List<EverythingModel>?>>()
    val everythingState: LiveData<UIState<List<EverythingModel>?>> = _everythingState
    override var page: Int = 1

    init {
        fetchNews(1)
    }

    override fun fetchNews(page: Int) {
        subscribeTo(_everythingState) {
            useCases.invoke(page)
        }
    }
}