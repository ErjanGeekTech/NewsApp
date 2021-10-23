package com.example.newsapp.extensions

import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.base.BaseFetchRequest
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun dateFormatTime(oldStringDate: String?): String? {
    val newDate: String?
    val dateFormat = SimpleDateFormat("E, d MMM yyyy", Locale(getCountry()))
    newDate = try {
        val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldStringDate)
        dateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        oldStringDate
    }
    return newDate
}

private fun getCountry(): String =
    Locale.getDefault().country.lowercase(Locale.ROOT)

fun RecyclerView.scrollListenerUploadNextPage(viewModel: BaseFetchRequest) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                viewModel.page++
                viewModel.fetchNews(viewModel.page)
            }
        }
    })
}