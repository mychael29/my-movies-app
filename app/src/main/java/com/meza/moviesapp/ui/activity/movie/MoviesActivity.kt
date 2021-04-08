package com.meza.moviesapp.ui.activity.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meza.domain.Constants.COUNT_LOAD_MORE
import com.meza.domain.Constants.IS_LOADING
import com.meza.domain.Constants.MOVIES_SAVE
import com.meza.domain.entity.Movie
import com.meza.domain.entity.User
import com.meza.moviesapp.BR
import com.meza.moviesapp.R
import com.meza.moviesapp.UserSingleton
import com.meza.moviesapp.databinding.ActivityMoviesBinding
import com.meza.moviesapp.ui.BaseActivity
import com.meza.moviesapp.ui.activity.movie.detail.MovieDetailFragment
import com.meza.moviesapp.ui.activity.splash.SplashActivity
import com.meza.moviesapp.util.extensions.launchActivity
import dagger.android.AndroidInjection.inject
import java.io.Serializable
import kotlin.properties.Delegates

class MoviesActivity : BaseActivity<MoviesViewModel, ActivityMoviesBinding>() {
    var countLoadMore by Delegates.notNull<Int>()
    var isLoading by Delegates.notNull<Boolean>()

    override fun getViewBinding() = ActivityMoviesBinding.inflate(layoutInflater)

    override fun getBindingVariable(): Int = BR.moviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
        savedInstanceState ?: viewModel.initiate()
        observeViewState()
        isLoading = false
        countLoadMore = 1
    }

    private fun observeViewState() {
        viewModel.movieList.observe(this, Observer {
            viewModel.bindItemsAfterMapping(it, isLoading)
            generatePagination()
        })

        viewModel.touchListenerItem.observe(this, Observer {
            showMovieDetailFragment()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_movies, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_fav -> {
            UserSingleton.setUser(User())
            launchActivity<SplashActivity>()
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun showMovieDetailFragment() {
        MovieDetailFragment.newInstance().show(supportFragmentManager, "MOVIE_DETAIL")
    }

    private fun pagination() {
        binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lms = recyclerView.layoutManager
                var lastVisiblePosition = 0
                when (lms) {
                    is LinearLayoutManager -> {
                        lastVisiblePosition = lms.findLastCompletelyVisibleItemPosition()
                    }
                    is GridLayoutManager -> {
                        lastVisiblePosition = lms.findLastCompletelyVisibleItemPosition()
                    }
                }
                val countItem = lms?.itemCount

                val isLastPosition = countItem?.minus(1) == lastVisiblePosition
                if (!isLoading && isLastPosition) {
                    isLoading = true
                    countLoadMore += 1
                    viewModel.refreshData(countLoadMore)
                }
            }
        })
    }

    private fun generatePagination() {
        if (!isLoading) countLoadMore = 1
        isLoading = false
        pagination()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        countLoadMore = outState.getInt(COUNT_LOAD_MORE)
        isLoading = outState.getBoolean(IS_LOADING)
        viewModel.saveStateMovieList()?.let { outState.putSerializable(MOVIES_SAVE, it as Serializable) }
        super.onSaveInstanceState(outState)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.getInt(COUNT_LOAD_MORE, countLoadMore)
        savedInstanceState.getBoolean(IS_LOADING, isLoading)
        savedInstanceState.getSerializable(MOVIES_SAVE)?.let { viewModel.restoredMovieList(it as List<Movie>) }
        super.onRestoreInstanceState(savedInstanceState)
    }

}