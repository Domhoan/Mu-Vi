package com.example.muvi.data.source.remote

import com.example.muvi.data.model.*
import com.example.muvi.data.source.MovieDataSource
import io.reactivex.rxjava3.core.Observable

class MovieRemoteDataSource(private val movieService: MovieService) : MovieDataSource.Remote {

    override fun getTrendingMovies(): Observable<MovieResponse> = movieService.getTrendingMovies()

    override fun getDiscoveryMovies(page: Int?): Observable<MovieResponse> =
        movieService.getDiscoveryMovies(page)

    override fun getTopRateMovies(page: Int?): Observable<MovieResponse> =
        movieService.getTopRateMovies(page)

    override fun getPopularMovies(page: Int?): Observable<MovieResponse> =
        movieService.getPopularMovies(page)

    override fun getRecommendMovies(movieId: Int): Observable<MovieResponse> =
        movieService.getRecommendMovies(movieId)

    override fun getActors(movieId: Int): Observable<DetailMovie> =
        movieService.getActors(movieId)

    override fun getVideo(movieId: Int): Observable<DetailMovie> =
        movieService.getVideo(movieId)

    override fun search(param: String): Observable<MovieResponse> =
        movieService.search(param)

    override fun getMoviesOfActor(actorId: Int): Observable<MovieResponse> =
        movieService.getMoviesOfActor(actorId)

    override fun getMovieByGenre(genreId: Int, page: Int?): Observable<MovieResponse> {
        return movieService.getMovieByGenre(genreId, page)
    }

    override fun getDetailMovie(movieId: Int): Observable<Movie> =
        movieService.getDetailMovie(movieId)

    override fun getUpComingMovies(date: String, page: Int?): Observable<MovieResponse> =
        movieService.getUpComingMovies(date, page)

    override fun getMoviesOfCompany(companyId: Int, page: Int?): Observable<MovieResponse> =
        movieService.getMoviesOfCompany(companyId,page)
}
