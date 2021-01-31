package com.example.muvi.data.source

import com.example.muvi.data.model.*
import io.reactivex.rxjava3.core.Observable

interface MovieDataSource {
    interface Remote {

        fun getTrendingMovies(): Observable<MovieResponse>

        fun getDiscoveryMovies(page: Int? = null): Observable<MovieResponse>

        fun getTopRateMovies(page: Int? = null): Observable<MovieResponse>

        fun getPopularMovies(page: Int? = null): Observable<MovieResponse>

        fun getRecommendMovies(movieId: Int): Observable<MovieResponse>

        fun getActors(movieId: Int): Observable<DetailMovie>

        fun getVideo(movieId: Int): Observable<DetailMovie>

        fun search(param: String): Observable<MovieResponse>

        fun getMoviesOfActor(actorId: Int): Observable<MovieResponse>

        fun getMovieByGenre(genreId: Int, page: Int? = null): Observable<MovieResponse>

        fun getDetailMovie(movieId: Int): Observable<Movie>

        fun getUpComingMovies(date: String, page: Int? = null): Observable<MovieResponse>

        fun getMoviesOfCompany(companyId : Int,page: Int? = null) : Observable<MovieResponse>
    }
}
