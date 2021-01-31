package com.example.muvi.data.repository

import com.example.muvi.data.model.*
import com.example.muvi.data.source.MovieDataSource
import com.example.muvi.utils.GenreUtil
import io.reactivex.rxjava3.core.Observable

class MovieRepositoryType(
    private val remote: MovieDataSource.Remote
) : MovieRepository {

    override fun getMoviesByType(type: MovieType, page: Int?): Observable<List<Movie>> =
        when (type) {
            MovieType.TopRate -> remote.getTopRateMovies(page)
            MovieType.Popular -> remote.getPopularMovies(page)
            MovieType.Discovery -> remote.getDiscoveryMovies(page)
            MovieType.Trending -> remote.getTrendingMovies()
        }.map { getMoviesType(it) }

    override fun getRecommendMovies(movieId: Int): Observable<List<Movie>> =
        remote.getRecommendMovies(movieId).map {
            getMoviesType(it).filter { movie ->
                !movie.poster.isNullOrEmpty()
            }
        }

    override fun getActors(movieId: Int): Observable<List<Actor>> =
        remote.getActors(movieId).map {
            it.cast?.filter { actor ->
                !actor.avatar.isNullOrEmpty()
            }
        }

    override fun getVideo(movieId: Int): Observable<Video> =
        remote.getVideo(movieId).map { getTrailer(it) }

    override fun search(param: String): Observable<List<Movie>> =
        remote.search(param).map {
            getMoviesType(it).filter { item ->
                !item.poster.isNullOrEmpty() && (item.movieType == Movie.MOVIE || item.movieType == Movie.PERSON)
            }
        }

    override fun getMoviesOfActor(actorId: Int): Observable<List<Movie>> =
        remote.getMoviesOfActor(actorId).map {
            getMoviesActor(it).filter {movie ->
                !movie.poster.isNullOrEmpty()
            }
        }

    override fun getMoviesByGenre(genreId: Int, page: Int?): Observable<List<Movie>> {
        return remote.getMovieByGenre(genreId, page).map {
            getMoviesType(it).filter { movie ->
                !movie.poster.isNullOrEmpty()
            }
        }
    }

    override fun getGenres(): Observable<List<Genre>> =
        Observable.just(GenreUtil.genres)

    override fun getDetailMovie(movieId: Int): Observable<Movie> =
        remote.getDetailMovie(movieId)

    override fun getUpComingMovie(date: String, page: Int?): Observable<List<Movie>> =
        remote.getUpComingMovies(date, page).map {
            getMoviesType(it).filter { movie ->
                movie.haveContent()
            }
        }

    override fun getMovieCompany(companyId: Int, page: Int?): Observable<List<Movie>> =
        remote.getMoviesOfCompany(companyId, page).map {
            getMoviesType(it).filter { movie ->
                !movie.poster.isNullOrEmpty()
            }
        }

    private fun getMoviesType(moviesResponse: MovieResponse): List<Movie> =
        moviesResponse.movies!!.map {
            Movie(it)
        }

    private fun getMoviesActor(moviesResponse: MovieResponse): List<Movie> =
        moviesResponse.movieOfActor!!.map {
            Movie(it)
        }

    private fun getTrailer(detailMovie: DetailMovie): Video =
        detailMovie.video?.first {
            it.type == Video.TYPE_TRAILER
        } ?: Video()
}
