package com.twaun95.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.twaun95.presentation.ui.search.SearchFragment
import com.twaun95.presentation.ui.storage.StorageFragment


class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = PAGE_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment.getInstance()
            else -> StorageFragment.getInstance()
        }
    }

    companion object {
        private const val PAGE_COUNT = 2
        const val PAGE_SEARCH = 0
        const val PAGE_STORAGE = 1
    }
}