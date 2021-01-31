package com.example.muvi.data.source.local

import com.example.muvi.data.model.Movie
import com.example.muvi.data.source.FavoriteDataSource
import com.example.muvi.data.source.local.database.dao.MovieDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class FavoriteLocalDataSource(
    private val movieDao: MovieDao
) : FavoriteDataSource.Local {

    override fun getFavorites(): Flowable<List<Movie>> =
        movieDao.getFavorites()

    override fun insertFavorites(movie: Movie): Completable =
        movieDao.insertMovie(movie)

    override fun deleteFavorites(movie: Movie): Completable =
        movieDao.delete(movie)

    override fun isFavorite(movieId: Int): Single<Boolean> =
        movieDao.isFavorite(movieId).map { it.isNotEmpty() }
}
