package com.twaun95.presentation.ui

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import com.twaun95.presentation.R
import com.twaun95.presentation.adapter.ViewPagerAdapter
import com.twaun95.presentation.base.BaseActivity
import com.twaun95.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun initView() {
        super.initView()

        initViewPager()
        initBottomNavigation()
    }

    private fun initViewPager() {
        binding.viewPager.apply {
            isUserInputEnabled = false
            adapter = ViewPagerAdapter(this@MainActivity)
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.apply {
            itemIconTintList = null
            setOnItemSelectedListener { page ->
                when(page.itemId) {
                    R.id.page_search -> {
                        binding.viewPager.setCurrentItem(ViewPagerAdapter.PAGE_SEARCH, false)
                        true
                    }
                    R.id.page_storage -> {
                        binding.viewPager.setCurrentItem(ViewPagerAdapter.PAGE_STORAGE, false)
                        true
                    }
                    else -> { false }
                }
            }
        }
    }

    fun downKeyboard(v: View) {
        v.clearFocus()
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    downKeyboard(v)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}