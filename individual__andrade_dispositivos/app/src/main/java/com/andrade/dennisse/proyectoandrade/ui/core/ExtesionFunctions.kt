// Archivo: ExtesionFunctions.kt
package com.andrade.dennisse.proyectoandrade.ui.core

import com.andrade.dennisse.proyectoandrade.data.network.entities.movies.Result as ResultMovies
import com.andrade.dennisse.proyectoandrade.ui.entities.MoviesInfoUI
import com.andrade.dennisse.proyectoandrade.data.network.entities.moviesRating.Result
import com.andrade.dennisse.proyectoandrade.ui.entities.favs.MoviesFavsInfoUI
import com.andrade.dennisse.proyectoandrade.ui.entities.rated.MoviesRatedInfoUI
import com.andrade.dennisse.proyectoandrade.ui.entities.rated.SeriesRatedInfoUI


import com.andrade.dennisse.proyectoandrade.data.network.entities.tvRating.Result as ResultSeries


fun ResultMovies.toMoviesInfoUI() = MoviesInfoUI(
    this.id,
    this.original_language,
    this.original_title,
    this.title,
    this.popularity,
    this.poster_path,
    this.overview,
    this.release_date
)

fun Result.toRatedMoviesInfoUI() = MoviesRatedInfoUI(
    this.id,
    this.original_title,
    this.title,
    this.original_language,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.vote_average,
    this.overview
)

fun ResultSeries.toRatedSeriesInfoUI() = SeriesRatedInfoUI(
    this.id,
    this.original_name,
    this.name,
    this.original_language,
    this.popularity,
    this.poster_path,
    this.first_air_date,
    this.vote_average,
    this.overview
)

