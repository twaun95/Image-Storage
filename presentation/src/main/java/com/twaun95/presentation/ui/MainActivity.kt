package com.twaun95.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.twaun95.presentation.R
import com.twaun95.presentation.adapter.ViewPagerAdapter
import com.twaun95.presentation.base.BaseActivity
import com.twaun95.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun initView() {
        super.initView()

        initViewPager()
        initBottomNavigation()
    }

    override fun setEvent() {
        super.setEvent()
    }

    override fun setObserver() {
        super.setObserver()
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
                        binding.viewPager.setCurrentItem(0, false)
                        true
                    }
                    R.id.page_storage -> {
                        binding.viewPager.setCurrentItem(1, false)
                        true
                    }
                    else -> { false }
                }
            }
        }
    }
}