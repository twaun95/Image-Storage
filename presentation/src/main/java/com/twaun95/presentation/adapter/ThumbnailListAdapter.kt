package com.twaun95.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.twaun95.domain.entity.Thumbnail
import com.twaun95.presentation.databinding.ItemSearchBinding

class ThumbnailListAdapter(

) : ListAdapter<Thumbnail, ThumbnailListAdapter.ThumbnailViewHolder>(diffUtil){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        return ThumbnailViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Thumbnail>() {
            override fun areItemsTheSame(oldItem: Thumbnail, newItem: Thumbnail): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Thumbnail, newItem: Thumbnail): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ThumbnailViewHolder(
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Thumbnail) {
            binding.data = data
        }
    }
}