package com.twaun95.presentation.ui

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridLayoutManager(
    context: Context
) : GridLayoutManager(context, SPAN_COUNT, VERTICAL, false) {

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
        lp.height = (height/ DIVIDE_BY).toInt()
        return super.checkLayoutParams(lp)
    }

    companion object {
        private const val SPAN_COUNT = 2
        private const val DIVIDE_BY = 2.1

    }
}