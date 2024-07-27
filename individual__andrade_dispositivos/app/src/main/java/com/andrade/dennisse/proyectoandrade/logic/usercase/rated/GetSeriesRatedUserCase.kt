package com.andrade.dennisse.proyectoandrade.logic.usercase.rated


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import android.util.Log
import com.andrade.dennisse.proyectoandrade.data.network.endpoints.MoviesEndPoints
import com.andrade.dennisse.proyectoandrade.data.network.repository.RetrofitBase
import com.andrade.dennisse.proyectoandrade.ui.core.toRatedSeriesInfoUI
import com.andrade.dennisse.proyectoandrade.ui.entities.rated.SeriesRatedInfoUI

class GetSeriesRatedUserCase {
    suspend operator fun invoke() = flow {
        val response = RetrofitBase.returnBaseRetrofitMovies()
            .create(MoviesEndPoints::class.java)
            .getTopRatedTVShows()

        Log.d("stop", response.toString())
        if (response.isSuccessful) {
            val x = response.body()?.results

            val items = ArrayList<SeriesRatedInfoUI>()

            x?.forEach {
                items.add(it.toRatedSeriesInfoUI())
            }

            Log.d("TAG", items.size.toString())
            emit(Result.success(items.toList()))
        } else {
            // Handle non-successful response
            emit(Result.failure(Exception("Error: ${response.errorBody()?.string()}")))
        }
    }.catch {
        Log.d("TAG", it.message.toString())
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}
