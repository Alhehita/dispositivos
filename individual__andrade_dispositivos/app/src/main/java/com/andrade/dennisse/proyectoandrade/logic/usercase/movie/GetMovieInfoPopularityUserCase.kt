package com.andrade.dennisse.proyectoandrade.logic.usercase.movie

import android.util.Log
import com.andrade.dennisse.proyectoandrade.data.network.endpoints.MoviesEndPoints
import com.andrade.dennisse.proyectoandrade.data.network.repository.RetrofitBase
import com.andrade.dennisse.proyectoandrade.ui.core.toMoviesInfoUI
import com.andrade.dennisse.proyectoandrade.ui.entities.MoviesInfoUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieInfoPopularityUserCase {

    suspend operator fun invoke() = flow {

        var response = RetrofitBase.returnBaseRetrofitMovies()
            .create(MoviesEndPoints::class.java)
            .getDiscoverMovies(false)

        Log.d("stop", response.toString())
        if (response.isSuccessful) {

            val x = response.body()?.results

            var items = ArrayList<MoviesInfoUI>()

            x?.forEach {
                items.add(it.toMoviesInfoUI())
            }
            Log.d("TAG", items.size.toString())
            emit(Result.success(items.toList()))
        }

    }.catch {
        Log.d("TAG", it.message.toString())
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}