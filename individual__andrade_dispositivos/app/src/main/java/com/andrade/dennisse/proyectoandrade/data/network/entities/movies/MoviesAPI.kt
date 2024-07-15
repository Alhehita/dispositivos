package com.andrade.dennisse.proyectoandrade.data.network.entities.movies

data class MoviesAPI(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)