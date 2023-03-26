package com.twaun95.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.twaun95.domain.entity.Thumbnail
import com.twaun95.presentation.databinding.ItemSearchBinding
import com.twaun95.presentation.extensions.setOnSingleClickListener

class ThumbnailListAdapter(
    var onClick: ((item: Thumbnail) -> Unit) ?= null
) : ListAdapter<Thumbnail, ThumbnailListAdapter.ThumbnailViewHolder>(diffUtil){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        return ThumbnailViewHolder(
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickListener = onClick
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).url.toLong()
    }

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Thumbnail>() {
            override fun areItemsTheSame(oldItem: Thumbnail, newItem: Thumbnail): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Thumbnail, newItem: Thumbnail): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ThumbnailViewHolder(
        private val binding: ItemSearchBinding,
        private val onClickListener: ((item: Thumbnail) -> Unit) ?= null
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: Thumbnail
        ) {
            binding.data = data
            binding.imageBookmark.setOnSingleClickListener {
                onClickListener?.invoke(data)
            }
        }
    }
}