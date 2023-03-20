package com.twaun95.presentation.ui.search

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.twaun95.presentation.R
import com.twaun95.presentation.base.BaseFragment
import com.twaun95.presentation.databinding.FragmentSearchBinding
import com.twaun95.presentation.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>(R.layout.fragment_search){
    override val fragmentVM by viewModels<SearchFragmentViewModel>()
    private val activityVM by activityViewModels<MainActivityViewModel>()

    override fun initView() {
        super.initView()
    }

    override fun setEvent() {
        super.setEvent()
    }

    override fun setObserver() {
        super.setObserver()
    }

    companion object {
        fun getInstance() : SearchFragment = SearchFragment()
    }
}