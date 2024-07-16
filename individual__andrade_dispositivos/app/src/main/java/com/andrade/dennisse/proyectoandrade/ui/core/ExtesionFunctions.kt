package com.andrade.dennisse.proyectoandrade.ui.core

import com.andrade.dennisse.proyectoandrade.ui.entities.MoviesInfoUI
import com.andrade.dennisse.proyectoandrade.data.network.entities.movies.Result


fun Result.toMoviesInfoUI() = MoviesInfoUI(

    this.id,
    this.original_language,
    this.original_title,
    this.title,
    this.popularity,
    this.poster_path

)
