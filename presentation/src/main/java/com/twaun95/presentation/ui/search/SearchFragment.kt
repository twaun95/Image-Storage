package com.twaun95.presentation.ui.search

import android.view.inputmethod.EditorInfo
import androidx.customview.widget.ViewDragHelper.DIRECTION_VERTICAL
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.recyclerview.widget.SimpleItemAnimator
import com.twaun95.domain.entity.Thumbnail
import com.twaun95.presentation.R
import com.twaun95.presentation.adapter.ThumbnailListAdapter
import com.twaun95.presentation.base.BaseFragment
import com.twaun95.presentation.databinding.FragmentSearchBinding
import com.twaun95.presentation.dialog.CommonDialog
import com.twaun95.presentation.extensions.setOnSingleClickListener
import com.twaun95.presentation.ui.MainActivity
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
        binding.buttonSearch.setOnSingleClickListener {
            fragmentVM.getSearchList()
        }
        binding.editTextSearch.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> { fragmentVM.getSearchList() }
                else -> {}
            }
            (activity as MainActivity).downKeyboard(v)
            true
        }
    }

    override fun setObserver() {
        super.setObserver()
        fragmentVM.thumbnailList
            .onEach {
                thumbnailAdapter.submitList(it)
            }
            .launchIn(this.lifecycleScope)

        fragmentVM.error.observe(viewLifecycleOwner){
            CommonDialog.show(
                parentFragmentManager,
                viewLifecycleOwner,
                getString(R.string.message_error),
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
                onClick = { item ->
                    if (item.isBookMarked) {
                        showDeleteDialog(item)
                    } else {
                        showSaveDialog(item)
                    }
                }
            }
            itemAnimator = (itemAnimator as SimpleItemAnimator).apply { supportsChangeAnimations = false }
            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if(!binding.recyclerViewSearch.canScrollVertically(DIRECTION_VERTICAL) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        fragmentVM.getSearchNextPage()
                    }
                }
            })
        }
    }

    private fun showSaveDialog(thumbnail: Thumbnail) {
        CommonDialog.show(
            parentFragmentManager,
            viewLifecycleOwner,
            getString(R.string.message_save),
            true,
            getString(R.string.cancel),
            {},
            getString(R.string.confirm),
            {
                fragmentVM.saveStorage(thumbnail)
                fragmentVM.getSearchList()
            }
        )
    }

    private fun showDeleteDialog(thumbnail: Thumbnail) {
        CommonDialog.show(
            parentFragmentManager,
            viewLifecycleOwner,
            getString(R.string.message_delete),
            true,
            getString(R.string.cancel),
            {},
            getString(R.string.confirm),
            {
                fragmentVM.deleteStorage(thumbnail)
                fragmentVM.getSearchList()
            }
        )
    }

    override fun onResume() {
        super.onResume()
        fragmentVM.getSearchList()
    }


    companion object {
        fun getInstance() : SearchFragment = SearchFragment()
    }
}