package com.meza.moviesapp.ui.activity.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.meza.domain.entity.Movie
import com.meza.moviesapp.BR
import com.meza.moviesapp.R
import com.meza.moviesapp.databinding.ActivityMoviesBinding
import com.meza.moviesapp.ui.BaseActivity
import dagger.android.AndroidInjection.inject
import java.io.Serializable

class MoviesActivity : BaseActivity<MoviesViewModel, ActivityMoviesBinding>() {

    override fun getViewBinding() = ActivityMoviesBinding.inflate(layoutInflater)

    override fun getBindingVariable(): Int = BR.moviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
        savedInstanceState ?: viewModel.initiate()
        observeViewState()
    }

    private fun observeViewState() {
        viewModel.movieList.observe(this, Observer {
            viewModel.bindItemsAfterMapping(it)
        })

        viewModel.touchListenerItem.observe(this, Observer {
            // startActivity(Intent(this, MovieDetailActivity::class.java))
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_movies, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_fav -> {
            //showDialogFragment()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveStateMovieList()?.let { outState.putSerializable("MOVIES_SAVE", it as Serializable) }
        super.onSaveInstanceState(outState)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.getSerializable("MOVIES_SAVE")?.let { viewModel.restoredMovieList(it as List<Movie>) }
        super.onRestoreInstanceState(savedInstanceState)
    }

}