package com.andrade.dennisse.proyectoandrade.logic.usercase.tv

import android.util.Log
import com.andrade.dennisse.proyectoandrade.data.network.endpoints.MoviesEndPoints
import com.andrade.dennisse.proyectoandrade.data.network.repository.RetrofitBase
import com.andrade.dennisse.proyectoandrade.ui.core.toTVInfoUI
import com.andrade.dennisse.proyectoandrade.ui.entities.tv.TVInfoUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
class GetTVInfoPopularityUserCase {

    suspend operator fun invoke() = flow {
        Log.d("GetTVInfoPopularityUserCase", "Iniciando llamada a la API")

        val response = RetrofitBase.returnBaseRetrofitMovies()
            .create(MoviesEndPoints::class.java)
            .getDiscoverTV(false)

        Log.d("GetTVInfoPopularityUserCase", "Respuesta de la API: $response")

        if (response.isSuccessful) {
            val x = response.body()?.results
            Log.d("GetTVInfoPopularityUserCase", "Resultados obtenidos: $x")

            val items = ArrayList<TVInfoUI>()
            x?.forEach {
                items.add(it.toTVInfoUI())
            }
            Log.d("GetTVInfoPopularityUserCase", "Items convertidos: $items")
            emit(Result.success(items.toList()))
        } else {
            Log.e("GetTVInfoPopularityUserCase", "Error en la respuesta de la API: ${response.errorBody()?.string()}")
            emit(Result.failure(Throwable("Error en la respuesta de la API")))
        }
    }.catch {
        Log.e("GetTVInfoPopularityUserCase", "Error en la llamada a la API: ${it.message}")
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}
