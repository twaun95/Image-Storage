package com.twaun95.presentation.extensions

import android.os.SystemClock
import android.view.View

class SetOnSingleClickListener(
    private val onClickListener: (view: View) -> Unit
) : View.OnClickListener{
    private var lastClickedTime = 0L

    override fun onClick(view: View) {
        val onClickedTime = SystemClock.elapsedRealtime()

        if ((onClickedTime-lastClickedTime) < INTERVAL) { return }

        lastClickedTime = onClickedTime
        onClickListener.invoke(view)
    }

    companion object {
        const val INTERVAL = 200
    }
}

fun View.setOnSingleClickListener(
    onClickListener: (view: View) -> Unit
) {
    setOnClickListener(SetOnSingleClickListener(onClickListener))
}