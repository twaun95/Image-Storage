package com.twaun95.presentation.ui.search

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.twaun95.presentation.R
import com.twaun95.presentation.adapter.ThumbnailListAdapter
import com.twaun95.presentation.base.BaseFragment
import com.twaun95.presentation.databinding.FragmentSearchBinding
import com.twaun95.presentation.dialog.CommonDialog
import com.twaun95.presentation.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>(R.layout.fragment_search){
    override val fragmentVM by viewModels<SearchFragmentViewModel>()
    private val activityVM by activityViewModels<MainActivityViewModel>()

    private val thumbnailAdapter by lazy { ThumbnailListAdapter() }

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
            CommonDialog.show(
                parentFragmentManager,
                viewLifecycleOwner,
                "이미지를 불러오시겠습니까?",
                true,
                getString(R.string.cancel),
                {},
                getString(R.string.confirm),
                {
                    fragmentVM.getThumbnailList()
                }
            )
        }
    }

    override fun setObserver() {
        super.setObserver()

        fragmentVM.thumbnailList
            .onEach {
                Timber.d("viewModel ${it.size}")
                thumbnailAdapter.submitList(it)
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

    private fun setRecyclerView() {
        binding.recyclerViewSearch.apply {
            layoutManager = SearchLayoutManager(requireContext())
            adapter = thumbnailAdapter.apply {
                onClick = {
                    CommonDialog.show(
                        parentFragmentManager,
                        viewLifecycleOwner,
                        "보관함에 저장하시겠습니까?",
                        true,
                        getString(R.string.cancel),
                        {
                            fragmentVM.deleteStorage(it)
                            fragmentVM.getStorage()
                        },
                        getString(R.string.confirm),
                        {
                            fragmentVM.saveStorage(it)
                            fragmentVM.getStorage()
                        }
                    )
                }
            }
        }
    }


    companion object {
        fun getInstance() : SearchFragment = SearchFragment()
    }
}