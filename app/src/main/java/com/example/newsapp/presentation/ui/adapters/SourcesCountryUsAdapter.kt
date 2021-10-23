package com.example.newsapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.base.BaseDiffUtilItemCallback
import com.example.newsapp.databinding.ItemSourcesCountryUsBinding
import com.example.newsapp.domain.models.Sources

class SourcesCountryUsAdapter :
    ListAdapter<Sources, SourcesCountryUsAdapter.SourcesCountryUsViewHolder>(
        BaseDiffUtilItemCallback<Sources>()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesCountryUsViewHolder {
        return SourcesCountryUsViewHolder(
            ItemSourcesCountryUsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SourcesCountryUsViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    class SourcesCountryUsViewHolder(private val binding: ItemSourcesCountryUsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(it: Sources) = with(binding) {
            name.text = it.name
            country.text = it.country
            desc.text = it.description
            category.text = it.category
            languageCountry.text = it.language
        }

    }
}