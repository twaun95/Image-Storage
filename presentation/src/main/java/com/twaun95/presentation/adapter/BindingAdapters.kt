package com.twaun95.presentation.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.twaun95.domain.entity.Thumbnail
import com.twaun95.presentation.R
import com.twaun95.presentation.ui.search.SearchFragment
import com.twaun95.presentation.ui.search.SearchFragmentViewModel
import java.text.SimpleDateFormat


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

@BindingAdapter("bind:setBookMarkImage")
fun setBookMarkImage(view: ImageView, isBookMarked: Boolean) {
    Glide.with(view.context)
        .asBitmap()
        .load(if (isBookMarked) R.drawable.bookmark_on else R.drawable.bookmark_off)
        .into(view)
}

@BindingAdapter("android:onKeyBoardSearch")
fun setOnKeyBoardSearch(view: EditText, fragmentVM: SearchFragmentViewModel) {
    view.setOnEditorActionListener { v, actionId, event ->
        when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> { fragmentVM.getSearchList() }
            else -> {}
        }
        true
    }
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("android:toDateTimeFormat")
fun setToDateTimeFormat(view: TextView, textValue: String) {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("yyyy년MM월dd일 HH시mm분12312311")

    view.text = formatter.format(parser.parse(textValue))
}
