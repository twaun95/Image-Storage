package com.twaun95.presentation.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("android:visible")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:imageUrl")
fun setImageUrl(view: ImageView, url: String) {
    Glide.with(view.context)
        .asBitmap()
        .centerCrop()
        .load(url)
//        .error(R.drawable.image_movie_thumbnail)
        .into(view)
}