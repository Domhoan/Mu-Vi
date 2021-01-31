package com.example.muvi.ui.genres

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.muvi.base.RxViewModel
import com.example.muvi.data.model.Genre
import com.example.muvi.data.model.Movie
import com.example.muvi.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class GenresViewModel(
    private val movieRepository: MovieRepository
) : RxViewModel() {

    private var currentPage: Int = 0
    private var currentGenre: Int = 0
    private val _movie = MutableLiveData<MutableList<Movie>>()
    private val _genre = MutableLiveData<List<Genre>>()
    val movies: LiveData<MutableList<Movie>>
        get() = _movie
    val genres: LiveData<List<Genre>>
        get() = _genre

    init {
        getGenre()
    }

    fun getMovies(genreId: Int) {
        if (currentGenre == genreId) {
            getData(currentGenre)
        } else {
            currentPage = 0
            currentGenre = genreId
            movies.value?.clear()
            getData(currentGenre)
        }
    }

    private fun getGenre() {
        movieRepository.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _genre.value = it
                },
                {
                    error.value = it.message
                }
            )
            .addTo(disposables)
    }

    private fun getData(genreId: Int) {
        movieRepository.getMoviesByGenre(genreId, ++currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (currentPage == 1) {
                        _movie.value = it as MutableList<Movie>
                    } else {
                        _movie.value?.addAll(it)
                    }
                },
                {
                    error.value = it.message
                    currentPage--
                }
            )
            .addTo(disposables)
    }
}
