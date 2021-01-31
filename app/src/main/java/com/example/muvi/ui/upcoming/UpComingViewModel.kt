package com.example.muvi.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.muvi.base.RxViewModel
import com.example.muvi.data.model.Movie
import com.example.muvi.data.model.Video
import com.example.muvi.data.repository.FavoriteRepository
import com.example.muvi.data.repository.MovieRepository
import com.example.muvi.utils.GenreUtil
import com.example.muvi.utils.TimeUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class UpComingViewModel(
    private val movieRepository: MovieRepository,
    private val favoriteRepository: FavoriteRepository
) : RxViewModel() {

    private var page = 0

    private val _movie = MutableLiveData<MutableList<Movie>>()
    val movies: LiveData<MutableList<Movie>>
        get() = _movie

    private val _video = MutableLiveData<Video>()
    val video: LiveData<Video>
        get() = _video

    init {
        getUpComingMovie()
    }

    fun getUpComingMovie() {
        movieRepository.getUpComingMovie(TimeUtil.getCurrentDate(), ++page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it.map { oneMovie ->
                        checkFavorite(oneMovie)
                        //                       getVideo(oneMovie)
                        val newReleaseDate = oneMovie.releaseDate?.let { it1 ->
                            TimeUtil.getDateToShow(
                                it1
                            )
                        }
                        val newGenre = GenreUtil.getGenreOfMovie(oneMovie.genreIds)
                        oneMovie.releaseDate = newReleaseDate
                        oneMovie.video = newGenre
                        if (oneMovie.background == null) {
                            oneMovie.background = oneMovie.poster
                        }
                    }
                    if (page == 1) {
                        _movie.value = it as MutableList<Movie>
                    } else _movie.value?.addAll(it)
                },
                {
                    error.value = it.message
                    page--
                }
            )
            .addTo(disposables)
    }

    private fun checkFavorite(movie: Movie) {
        favoriteRepository.isFavorite(movie.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    movie.isFavorite = it
                },
                {
                    error.value = it.message
                }
            )
            .addTo(disposables)
    }

    private fun getVideo(movie: Movie) {
        movieRepository.getVideo(movie.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    movie.video = it.key
                },
                {
                    error.value = it.message
                }
            )
            .addTo(disposables)
    }

    fun updateFavorite(movie: Movie) {
        _movie.value?.let { it ->
            val index = it.indexOf(movie)
            var movieFavorite = movie.isFavorite
            if (movieFavorite == true) {
                favoriteRepository.deleteFavorite(movie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            movie.isFavorite = false
                            it.removeAt(index)
                            it[index] = movie
                        },
                        {
                            error.value = it.message
                        }
                    )
                    .addTo(disposables)
            } else {
                favoriteRepository.insertFavorite(movie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            movie.isFavorite = true
                            it[it.indexOf(movie)].isFavorite = true
                        },
                        {
                            error.value = it.message
                        }
                    )
                    .addTo(disposables)
            }
        }
    }
}
