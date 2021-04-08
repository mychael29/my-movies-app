package com.meza.moviesapp.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meza.moviesapp.ui.adapter.MovieAdapter

@BindingAdapter("movieAdapter")
fun setMovieAdapter(recyclerView: RecyclerView, adapter: MovieAdapter){
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = adapter
}