package com.example.muvi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.muvi.base.RxViewModel
import com.example.muvi.data.model.Movie
import com.example.muvi.data.model.MovieType
import com.example.muvi.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(
    private val movieRepository: MovieRepository
) : RxViewModel() {

    private val _movieTrending = MutableLiveData<MutableList<Movie>>()
    val moviesTrending: LiveData<MutableList<Movie>>
        get() = _movieTrending

    private val _movieDiscovery = MutableLiveData<MutableList<Movie>>()
    val moviesDiscovery: LiveData<MutableList<Movie>>
        get() = _movieDiscovery

    private val _movieTopRate = MutableLiveData<MutableList<Movie>>()
    val moviesTopRate: LiveData<MutableList<Movie>>
        get() = _movieTopRate

    private val _moviePopular = MutableLiveData<MutableList<Movie>>()
    val moviesPopular: LiveData<MutableList<Movie>>
        get() = _moviePopular

    init {
        getMovieTrending()
        getMovieDiscovery()
        getMovieTopRate()
        getMoviePopular()
    }

    private fun getMovieTrending() {
        movieRepository.getMoviesByType(MovieType.Trending, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _movieTrending.value = it as MutableList<Movie>?
            }, {
                error.value = it.message
            })
            .addTo(disposables)
    }

    private fun getMovieDiscovery() {
        movieRepository.getMoviesByType(MovieType.Discovery, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _movieDiscovery.value = it as MutableList<Movie>?
            }, {
                error.value = it.message
            })
            .addTo(disposables)
    }

    private fun getMovieTopRate() {
        movieRepository.getMoviesByType(MovieType.TopRate, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _movieTopRate.value = it as MutableList<Movie>?
            }, {
                error.value = it.message
            })
            .addTo(disposables)
    }

    private fun getMoviePopular() {
        movieRepository.getMoviesByType(MovieType.Popular, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _moviePopular.value = it as MutableList<Movie>?
            }, {
                error.value = it.message
            })
            .addTo(disposables)
    }
}
