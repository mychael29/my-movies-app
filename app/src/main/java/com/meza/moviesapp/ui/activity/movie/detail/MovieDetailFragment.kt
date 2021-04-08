package com.meza.moviesapp.ui.activity.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.meza.moviesapp.BR
import com.meza.moviesapp.R
import com.meza.moviesapp.databinding.FragmentMovieDetailBinding
import com.meza.moviesapp.ui.activity.movie.MoviesViewModel
import com.meza.moviesapp.util.DialogUtil

class MovieDetailFragment: DialogFragment() {
    private lateinit var viewDataBinding: FragmentMovieDetailBinding
    private lateinit var rootView: View
    private val viewModel: MoviesViewModel by activityViewModels()

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        rootView = viewDataBinding.root
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindDataBinding()
    }

    private fun bindDataBinding() {
        viewDataBinding.setVariable(BR.modelMovie, viewModel.touchListenerItem.value)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()
    }

    override fun onStart() {
        super.onStart()
        DialogUtil.configDialogFragment(this, resources)
    }
}