package com.andrade.dennisse.proyectoandrade.data.network.endpoints

import com.andrade.dennisse.proyectoandrade.data.network.entities.movies.MoviesAPI
import com.andrade.dennisse.proyectoandrade.data.network.entities.moviesRating.MoviesRatedApi
import com.andrade.dennisse.proyectoandrade.data.network.entities.tv.TVApi
import com.andrade.dennisse.proyectoandrade.data.network.entities.tvRating.SeriesRatedApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesEndPoints {
    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "es-LA",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"

    ): Response<MoviesAPI>

    @GET("discover/tv")
    suspend fun getDiscoverTV(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_null_first_air_dates") includeNullFirstAirDates: Boolean = false,
        @Query("language") language: String = "es-LA",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Response<TVApi>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "es-LA",
        @Query("page") page: Int = 1
    ): Response<MoviesRatedApi>


    @GET("tv/top_rated")
    suspend fun getTopRatedTVShows(
        @Query("language") language: String = "es-LA",
        @Query("page") page: Int = 1
    ): Response<SeriesRatedApi>




}

//IMPORTANTE

//REVISSAR PORQUE DONDE ESTA "Resposne" era Call y la funcion no tiene suspend