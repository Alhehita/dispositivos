package com.andrade.dennisse.proyectoandrade.ui.viewmodels.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrade.dennisse.proyectoandrade.logic.usercase.movie.GetMovieInfoPopularityUserCase
import com.andrade.dennisse.proyectoandrade.logic.usercase.tv.GetTVInfoPopularityUserCase
import com.andrade.dennisse.proyectoandrade.ui.core.UIStates
import com.andrade.dennisse.proyectoandrade.ui.entities.MoviesInfoUI
import com.andrade.dennisse.proyectoandrade.ui.entities.tv.TVInfoUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
class ListarTVInfoVM : ViewModel() {

    val itemsTV = MutableLiveData<List<TVInfoUI>>()
    val uiState = MutableLiveData<UIStates>()

    fun initData() {
        Log.d("ListarTVInfoVM", "Ingresando al ViewModel")
        viewModelScope.launch {
            uiState.postValue(UIStates.Loading(true))
            GetTVInfoPopularityUserCase().invoke().collect { respuesta ->
                respuesta.onSuccess { items ->
                    Log.d("ListarTVInfoVM", "Datos obtenidos con éxito: $items")
                    itemsTV.postValue(items)
                }
                respuesta.onFailure {
                    Log.e("ListarTVInfoVM", "Error al obtener datos: ${it.message}")
                    uiState.postValue(UIStates.Error(it.message.toString()))
                }
            }
            delay(500)
            uiState.postValue(UIStates.Loading(false))
        }
    }
}
