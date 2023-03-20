package com.twaun95.presentation.ui.storage

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.twaun95.presentation.R
import com.twaun95.presentation.base.BaseFragment
import com.twaun95.presentation.databinding.FragmentStorageBinding
import com.twaun95.presentation.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorageFragment : BaseFragment<FragmentStorageBinding, StorageFragmentViewModel>(R.layout.fragment_storage){
    override val fragmentVM by viewModels<StorageFragmentViewModel>()
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
        fun getInstance() : StorageFragment = StorageFragment()
    }
}