package com.twaun95.presentation.ui.search

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.twaun95.presentation.R
import com.twaun95.presentation.adapter.SearchListAdapter
import com.twaun95.presentation.adapter.VideoListAdapter
import com.twaun95.presentation.base.BaseFragment
import com.twaun95.presentation.databinding.FragmentSearchBinding
import com.twaun95.presentation.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>(R.layout.fragment_search){
    override val fragmentVM by viewModels<SearchFragmentViewModel>()
    private val activityVM by activityViewModels<MainActivityViewModel>()

    private val searchAdapter by lazy { SearchListAdapter() }
    private val videoAdapter by lazy { VideoListAdapter() }

    override fun initView() {
        super.initView()
        binding.fragment = this
        binding.fragmentVM = this.fragmentVM
        binding.activityVM = this.activityVM

        setRecyclerView()
    }

    override fun setEvent() {
        super.setEvent()
        binding.buttonSearch.setOnClickListener {
//            fragmentVM.getSearchList()
            fragmentVM.getVideoList()
        }
    }

    override fun setObserver() {
        super.setObserver()

        fragmentVM.imageList
            .onEach {
                Timber.d("viewModel ${it.size}")
                searchAdapter.submitList(it)
            }
            .launchIn(this.lifecycleScope)

        fragmentVM.videoList
            .onEach {
                Timber.d("viewModel ${it.size}")
                videoAdapter.submitList(it)
            }
            .launchIn(this.lifecycleScope)
    }

    private fun setRecyclerView() {
        binding.recyclerViewSearch.apply {
//            adapter = searchAdapter
            adapter = videoAdapter
        }
    }

    companion object {
        fun getInstance() : SearchFragment = SearchFragment()
    }
}