package com.example.muvi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.muvi.base.RxViewModel
import com.example.muvi.data.model.*
import com.example.muvi.data.repository.FavoriteRepository
import com.example.muvi.data.repository.MovieRepository
import com.example.muvi.utils.GenreUtil
import com.example.muvi.utils.TimeUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailViewModel(
    private val movieRepository: MovieRepository,
    private val favoriteRepository: FavoriteRepository
) : RxViewModel() {

    private val _detail = MutableLiveData<Movie>()
    val detail: LiveData<Movie>
        get() = _detail

    private val _video = MutableLiveData<Video>()
    val video: LiveData<Video>
        get() = _video

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val _recommends = MutableLiveData<List<Movie>>()
    val recommends: LiveData<List<Movie>>
        get() = _recommends

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>>
        get() = _actors

    val companies = MutableLiveData<List<Company>>()
    var typeMovie = MutableLiveData<String>()

    fun loadDetail(movieId: Int) {
        getDetail(movieId)
        getActors(movieId)
        getRecommend(movieId)
        getVideo(movieId)
        checkFavorite(movieId)
    }

    private fun getDetail(movieId: Int) {
        movieRepository.getDetailMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.background == null) {
                        it.background = it.poster
                    }
                    val timeReleaseFormat = it.releaseDate?.let { time ->
                        TimeUtil.formatDateDDMMYYY(time)
                    }
                    it.releaseDate = timeReleaseFormat
                    _detail.value = it
                    getCompanies(it)
                    getTypeMovie(it.genres)
                },
                {
                    error.value = it.message
                }
            )
            .addTo(disposables)
    }

    private fun getActors(movieId: Int) {
        movieRepository.getActors(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _actors.value = it
                },
                {
                    error.value = it.message
                }
            )
            .addTo(disposables)
    }

    private fun checkFavorite(movieId: Int) {
        favoriteRepository.isFavorite(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _isFavorite.value = it
                },
                {
                    error.value = it.message
                }
            )
            .addTo(disposables)
    }

    private fun getRecommend(movieId: Int) {
        movieRepository.getRecommendMovies(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _recommends.value = it
                },
                {
                    error.value = it.message
                }
            )
            .addTo(disposables)
    }

    private fun getVideo(movieId: Int) {
        movieRepository.getVideo(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _video.value = it
                },
                {
                    error.value = it.message
                }
            )
            .addTo(disposables)
    }

    fun updateFavorite() {
        detail.value?.let { it ->
            val movieFavorite = Movie(it)
            if (isFavorite.value == true) {
                favoriteRepository.deleteFavorite(movieFavorite)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            _isFavorite.value = false
                        },
                        {
                            error.value = it.message
                        }
                    )
                    .addTo(disposables)
            } else {
                favoriteRepository.insertFavorite(movieFavorite)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            _isFavorite.value = true
                        },
                        {
                            error.value = it.message
                        }
                    )
                    .addTo(disposables)
            }
        }
    }

    private fun getCompanies(movie: Movie) {
        companies.value = movie.productionCompanies?.filter { company ->
            company.logo != null
        }
    }

    private fun getTypeMovie(genres: List<Genre>?) {
        genres?.let {
            typeMovie.value = it.joinToString("\t•\t") { genre ->
                genre.name
            }
        }
    }
}
