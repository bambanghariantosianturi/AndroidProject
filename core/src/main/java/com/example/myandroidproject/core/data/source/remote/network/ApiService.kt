package com.example.myandroidproject.core.data.source.remote.network

import com.example.myandroidproject.core.BuildConfig
import com.example.myandroidproject.core.data.source.remote.response.detailmovieresponse.DetailMovieResponse
import com.example.myandroidproject.core.data.source.remote.response.genremovieresponse.GenreMovieResponse
import com.example.myandroidproject.core.data.source.remote.response.listmovieresponse.ListMoviesResponse
import com.example.myandroidproject.core.data.source.remote.response.movietrailerresponse.ListMovieTrailerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovieList(
        @Query("api_key") movieApiKey: String? = BuildConfig.API_KEY,
        @Query("page") page: Int?,
        @Query("with_genres") genreId: Int?
    ): Call<ListMoviesResponse>

    @GET("movie/{movieId}")
    fun getDetailMovie(
        @Path("movieId") movieId: Int?,
        @Query("api_key") movieApiKey: String? = BuildConfig.API_KEY
    ): Call<DetailMovieResponse>

    @GET("genre/movie/list")
    fun getGenreMovies(
        @Query("api_key") movieApiKey: String? = BuildConfig.API_KEY
    ): Call<GenreMovieResponse>

    @GET("movie/{movieId}/videos")
    fun getMovieTrailer(
        @Path("movieId") movieId: Int?,
        @Query("api_key") movieApiKey: String? = BuildConfig.API_KEY
    ): Call<ListMovieTrailerResponse>
}