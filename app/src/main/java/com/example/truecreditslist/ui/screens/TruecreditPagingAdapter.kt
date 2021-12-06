package com.example.truecreditslist.ui.screens

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.truecreditslist.data.TruecreditsPost
import com.example.truecreditslist.ui.TruecreditListViewHolder


class TruecreditPagingAdapter()
    : PagingDataAdapter<TruecreditsPost, TruecreditListViewHolder>(POST_COMPARATOR) {

    override fun onBindViewHolder(holder: TruecreditListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: TruecreditListViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            val item = getItem(position)
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruecreditListViewHolder {
        return TruecreditListViewHolder.create(parent)
    }

    companion object {
        private val PAYLOAD_SCORE = Any()
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<TruecreditsPost>() {
            override fun areContentsTheSame(oldItem: TruecreditsPost, newItem: TruecreditsPost): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: TruecreditsPost, newItem: TruecreditsPost): Boolean =
                oldItem.name == newItem.name

            override fun getChangePayload(oldItem: TruecreditsPost, newItem: TruecreditsPost): Any? {
                return PAYLOAD_SCORE
            }
        }

    }
}
