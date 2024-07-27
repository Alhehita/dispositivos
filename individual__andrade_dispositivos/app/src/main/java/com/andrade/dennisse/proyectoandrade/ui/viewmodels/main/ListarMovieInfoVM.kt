package com.andrade.dennisse.proyectoandrade.ui.viewmodels.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrade.dennisse.proyectoandrade.logic.usercase.movie.GetMovieInfoPopularityUserCase
import com.andrade.dennisse.proyectoandrade.ui.core.UIStates
import com.andrade.dennisse.proyectoandrade.ui.entities.MoviesInfoUI
import kotlinx.coroutines.launch

class ListarMovieInfoVM : ViewModel() {

    val itemsMovies = MutableLiveData<List<MoviesInfoUI>>()
    val uiState = MutableLiveData<UIStates>()

    fun initData() {
        Log.d("TAG", "Ingresando al VM")
        viewModelScope.launch {
            uiState.postValue(UIStates.Loading(true))
            GetMovieInfoPopularityUserCase().invoke().collect { respuesta ->
                respuesta.onSuccess { items ->
                    itemsMovies.postValue(items)
                }
                respuesta.onFailure {
                    uiState.postValue(UIStates.Error(it.message.toString()))
                    Log.d("TAG", it.message.toString())
                }
            }
            uiState.postValue(UIStates.Loading(false))
        }
    }

}
