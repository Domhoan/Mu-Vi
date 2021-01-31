package com.example.muvi.ui.actor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.muvi.base.RxViewModel
import com.example.muvi.data.model.Movie
import com.example.muvi.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieOfActorViewModel(
    private val movieRepository: MovieRepository
) : RxViewModel() {

    private val _movie = MutableLiveData<MutableList<Movie>>()
    val movies: LiveData<MutableList<Movie>>
        get() = _movie

    fun getData(actorId: Int) {
        movieRepository.getMoviesOfActor(actorId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _movie.value = it as MutableList<Movie>
                },
                {
                    error.value = it.message
                }
            )
            .addTo(disposables)
    }
}
