package com.meza.moviesapp.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visible")
fun View.bindVisible(boolean: Boolean?){
    alpha = 0.2f
    animate().alpha(1f)
    visibility = if (boolean == true) View.VISIBLE else View.GONE
}

