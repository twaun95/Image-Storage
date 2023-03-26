package com.twaun95.presentation.extensions

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

fun View.setVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}

