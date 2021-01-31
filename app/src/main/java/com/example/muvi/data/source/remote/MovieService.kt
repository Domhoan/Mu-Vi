package com.example.muvi.data.source.remote

import com.example.muvi.data.model.*
import com.example.muvi.utils.ApiEndPoint
import com.example.muvi.utils.ApiEndPoint.PARAMS_DATE
import com.example.muvi.utils.ApiEndPoint.PARAMS_GENRE_ID
import com.example.muvi.utils.ApiEndPoint.PARAMS_ID
import com.example.muvi.utils.ApiEndPoint.PARAMS_PAGE
import com.example.muvi.utils.ApiEndPoint.PARAMS_WITH_COMPANY
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(ApiEndPoint.GET_TRENDING_MOVIE)
    fun getTrendingMovies(): Observable<MovieResponse>

    @GET(ApiEndPoint.GET_DISCOVERY_MOVIE)
    fun getDiscoveryMovies(@Query(PARAMS_PAGE) page: Int? = null): Observable<MovieResponse>

    @GET(ApiEndPoint.GET_TOP_RATE_MOVIE)
    fun getTopRateMovies(@Query(PARAMS_PAGE) page: Int? = null): Observable<MovieResponse>

    @GET(ApiEndPoint.GET_POPULAR_MOVIE)
    fun getPopularMovies(@Query(PARAMS_PAGE) page: Int? = null): Observable<MovieResponse>

    @GET(ApiEndPoint.GET_RECOMMEND_MOVIE)
    fun getRecommendMovies(@Path(PARAMS_ID) movieId: Int): Observable<MovieResponse>

    @GET(ApiEndPoint.GET_ACTOR_OF_MOVIE)
    fun getActors(@Path(PARAMS_ID) movieId: Int): Observable<DetailMovie>

    @GET(ApiEndPoint.GET_VIDEO)
    fun getVideo(@Path(PARAMS_ID) movieId: Int): Observable<DetailMovie>

    @GET(ApiEndPoint.SEARCH_MULTI)
    fun search(@Query("query") param: String): Observable<MovieResponse>

    @GET(ApiEndPoint.GET_MOVIE_OF_ACTOR)
    fun getMoviesOfActor(@Path(PARAMS_ID) actorId: Int): Observable<MovieResponse>

    @GET(ApiEndPoint.GET_MOVIE_BY_GENRE)
    fun getMoviesOfCompany(
        @Query(PARAMS_WITH_COMPANY) companyId: Int,
        @Query(PARAMS_PAGE) page: Int?
    ): Observable<MovieResponse>

    @GET(ApiEndPoint.GET_MOVIE_BY_GENRE)
    fun getMovieByGenre(
        @Query(PARAMS_GENRE_ID) genreId: Int,
        @Query(PARAMS_PAGE) page: Int? = null
    ): Observable<MovieResponse>

    @GET(ApiEndPoint.GET_DETAIL_MOVIE)
    fun getDetailMovie(@Path(PARAMS_ID) movieId: Int): Observable<Movie>

    @GET(ApiEndPoint.GET_UPCOMING_MOVIE)
    fun getUpComingMovies(
        @Query(PARAMS_DATE) date: String,
        @Query(PARAMS_PAGE) page: Int? = null
    ): Observable<MovieResponse>

}
