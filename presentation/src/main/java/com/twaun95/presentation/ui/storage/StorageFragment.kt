package com.twaun95.presentation.ui.storage

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.twaun95.presentation.R
import com.twaun95.presentation.adapter.ThumbnailListAdapter
import com.twaun95.presentation.base.BaseFragment
import com.twaun95.presentation.databinding.FragmentStorageBinding
import com.twaun95.presentation.dialog.CommonDialog
import com.twaun95.presentation.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class StorageFragment : BaseFragment<FragmentStorageBinding, StorageFragmentViewModel>(R.layout.fragment_storage){
    override val fragmentVM by viewModels<StorageFragmentViewModel>()
    private val activityVM by activityViewModels<MainActivityViewModel>()

    private val storageAdapter by lazy { ThumbnailListAdapter() }

    override fun initView() {
        super.initView()
        binding.fragment = this
        binding.fragmentVM = this.fragmentVM
        binding.activityVM = this.activityVM

        setRecyclerView()
    }

    override fun setEvent() {
        super.setEvent()
    }

    override fun setObserver() {
        super.setObserver()

        fragmentVM.storageList
            .onEach {
                Timber.d("viewModel ${it.size}")
                storageAdapter.submitList(it)
            }
            .launchIn(this.lifecycleScope)

        fragmentVM.error.observe(viewLifecycleOwner){
            CommonDialog.show(
                parentFragmentManager,
                viewLifecycleOwner,
                "리스트를 불러오는데 실패하였습니다.\n다시 시도해주세요.\n($it)",
                false,
                positiveName = getString(R.string.confirm),
                positiveAction = {}
            )
        }
    }

    override fun onResume() {
        super.onResume()

        fragmentVM.getThumbnailList()
    }

    private fun setRecyclerView() {
        binding.recyclerViewStorage.apply {
            layoutManager = StorageLayoutManager(requireContext())
            adapter = storageAdapter.apply {
                onClick = {
                    CommonDialog.show(
                        parentFragmentManager,
                        viewLifecycleOwner,
                        "보관함에서 지우시겠습니까?",
                        true,
                        getString(R.string.cancel),
                        {},
                        getString(R.string.confirm),
                        { fragmentVM.delete(it) }
                    )
                }
            }
        }
    }

    companion object {
        fun getInstance() : StorageFragment = StorageFragment()
    }
}