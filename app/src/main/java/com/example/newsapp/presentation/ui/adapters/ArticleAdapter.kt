package com.example.newsapp.presentation.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.newsapp.base.BaseDiffUtilItemCallback
import com.example.newsapp.databinding.ItemEverythingNewsBinding
import com.example.newsapp.domain.models.EverythingModel
import com.example.newsapp.extensions.dateFormatTime


class ArticleAdapter :
    ListAdapter<EverythingModel, ArticleAdapter.EverythingViewHolder>(
        BaseDiffUtilItemCallback<EverythingModel>()
    ) {

    override fun onBindViewHolder(holder: EverythingViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EverythingViewHolder {
        return EverythingViewHolder(
            ItemEverythingNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class EverythingViewHolder(private val binding: ItemEverythingNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(it: EverythingModel) = with(binding) {
            Glide.with(img)
                .load(it.urlToImage)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressLoadPhoto.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressLoadPhoto.visibility = View.GONE
                        return false
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img)

            title.text = it.title
            desc.text = it.description
            source.text = it.source?.name
            time.text = dateFormatTime(it.publishedAt)
            publishedAt.text = dateFormatTime(it.publishedAt)
            author.text = it.author
        }
    }
}