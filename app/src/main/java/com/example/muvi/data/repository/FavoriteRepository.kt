package com.example.muvi.data.repository

import com.example.muvi.data.model.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface FavoriteRepository {

    fun getFavorite(): Flowable<List<Movie>>

    fun insertFavorite(movie: Movie): Completable

    fun deleteFavorite(movie: Movie): Completable

    fun isFavorite(movieId: Int): Single<Boolean>
}
