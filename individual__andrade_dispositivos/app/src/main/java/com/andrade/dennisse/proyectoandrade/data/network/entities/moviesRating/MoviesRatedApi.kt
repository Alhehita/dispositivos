package com.andrade.dennisse.proyectoandrade.data.network.entities.moviesRating

data class MoviesRatedApi(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)