package com.example.muvi.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.muvi.base.RxViewModel
import com.example.muvi.data.model.Movie
import com.example.muvi.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel (
    private val movieRepository: MovieRepository
) : RxViewModel() {

    private val _search = MutableLiveData<List<Movie>>()
    val search: LiveData<List<Movie>>
    get() = _search

    fun search(query: String) {
        movieRepository.search(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _search.value = it
                },
                {
                    error.value = it.message
                }
            )
            .addTo(disposables)
    }
}
