package com.example.muvi.data.source.local.database.dao

import androidx.room.*
import com.example.muvi.data.model.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getFavorites(): Flowable<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movie: Movie): Completable

    @Delete
    fun delete(movie: Movie): Completable

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun isFavorite(movieId: Int): Single<List<Movie>>
}
