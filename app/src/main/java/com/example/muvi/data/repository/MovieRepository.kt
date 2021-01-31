package com.example.muvi.data.repository

import com.example.muvi.data.model.*
import io.reactivex.rxjava3.core.Observable

interface MovieRepository {

    fun getMoviesByType(type: MovieType, page: Int? = null): Observable<List<Movie>>

    fun getRecommendMovies(movieId: Int): Observable<List<Movie>>

    fun getActors(movieId: Int): Observable<List<Actor>>

    fun getVideo(movieId: Int): Observable<Video>

    fun search(param: String): Observable<List<Movie>>

    fun getMoviesOfActor(actorId: Int): Observable<List<Movie>>

    fun getMoviesByGenre(genreId: Int, page: Int? = null): Observable<List<Movie>>

    fun getGenres(): Observable<List<Genre>>

    fun getDetailMovie(movieId: Int): Observable<Movie>

    fun getUpComingMovie(date : String, page: Int?) : Observable<List<Movie>>

    fun getMovieCompany(companyId : Int,page: Int?) : Observable<List<Movie>>
}
