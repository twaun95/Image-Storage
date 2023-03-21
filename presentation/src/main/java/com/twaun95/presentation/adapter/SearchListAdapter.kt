package com.twaun95.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.twaun95.domain.model.ImageEntity
import com.twaun95.presentation.databinding.ItemSearchBinding

class SearchListAdapter(

) : ListAdapter<ImageEntity, SearchListAdapter.SearchViewHolder>(diffUtil){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<ImageEntity>() {
            override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class SearchViewHolder(
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: ImageEntity
        ) {
            binding.data = data
        }
    }

}