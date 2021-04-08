package com.meza.moviesapp.ui.binding

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.meza.moviesapp.R


@BindingAdapter("visible")
fun View.bindVisible(boolean: Boolean?){
    alpha = 0.2f
    animate().alpha(1f)
    visibility = if (boolean == true) View.VISIBLE else View.GONE
}

@BindingAdapter("urlImageGlide")
fun ImageView.bindGlide(urlImage: String){
    Glide.with(this.context)
        .load(urlImage)
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_broken_image)
        .into(this)
}
