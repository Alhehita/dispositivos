package com.andrade.dennisse.proyectoandrade.data.network.endpoints

import com.andrade.dennisse.proyectoandrade.data.network.entities.movies.MoviesAPI
import com.andrade.dennisse.proyectoandrade.data.network.entities.tv.TVApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesEndPoints {
    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"

    ): Response<MoviesAPI>

    @GET("discover/tv")
    suspend fun getDiscoverTV(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_null_first_air_dates") includeNullFirstAirDates: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Response<TVApi>
}

//IMPORTANTE

//REVISSAR PORQUE DONDE ESTA "Resposne" era Call y la funcion no tiene suspend