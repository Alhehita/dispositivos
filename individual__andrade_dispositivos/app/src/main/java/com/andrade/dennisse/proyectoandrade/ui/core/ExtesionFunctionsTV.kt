package com.andrade.dennisse.proyectoandrade.ui.core

import com.andrade.dennisse.proyectoandrade.ui.entities.tv.TVInfoUI
import com.andrade.dennisse.proyectoandrade.data.network.entities.tv.Result


fun Result.toTVInfoUI() = TVInfoUI(

    this.id,
    this.original_language,
    this.original_name,
    this.name,
    this.popularity,
    this.poster_path,
    this.overview,
    this.first_air_date,

)