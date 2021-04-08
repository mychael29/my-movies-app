package com.meza.moviesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.meza.moviesapp.BR
import com.meza.moviesapp.R
import com.meza.moviesapp.databinding.ItemMovieBinding
import com.meza.moviesapp.model.MovieModel

class MovieAdapter (private val items: MutableList<MovieModel>,
                    val callback:(model: MovieModel, position: Int) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding : ItemMovieBinding? = DataBindingUtil.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
        return MovieHolder(binding!!)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val todoModel = items[position]
        holder.setItem(todoModel)
        holder.itemMovieBinding.root.setOnClickListener {
            callback(todoModel, position)
        }
    }

    fun addItems(newItems: List<MovieModel>, isNextPage: Boolean) {
        if (!isNextPage) items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class MovieHolder(val itemMovieBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemMovieBinding.root) {
        fun setItem(model: MovieModel) {
            itemMovieBinding.setVariable(BR.modelMovie, model)
            itemMovieBinding.executePendingBindings()
        }
    }

    override fun getItemCount() = items.size
}