package com.example.muvi.data.source

import com.example.muvi.data.model.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface FavoriteDataSource {

    interface Local {
        fun getFavorites(): Flowable<List<Movie>>

        fun insertFavorites(movie: Movie): Completable

        fun deleteFavorites(movie: Movie): Completable

        fun isFavorite(movieId: Int): Single<Boolean>
    }
}
