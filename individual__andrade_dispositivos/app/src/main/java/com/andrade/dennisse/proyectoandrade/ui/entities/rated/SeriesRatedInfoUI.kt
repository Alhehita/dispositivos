package com.andrade.dennisse.proyectoandrade.ui.entities.rated


data class SeriesRatedInfoUI(
    val id: Int,
    val original_name: String,
    val name: String,
    val original_language: String,
    val popularity: Double,
    val poster_path: String,
    val first_air_date: String ,
    val vote_average: Double,
    val overview:String
)

