package com.andrade.dennisse.proyectoandrade.ui.viewmodels.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrade.dennisse.proyectoandrade.logic.usercase.rated.GetMovieRatedUserCase
import com.andrade.dennisse.proyectoandrade.logic.usercase.rated.GetSeriesRatedUserCase
import com.andrade.dennisse.proyectoandrade.ui.core.UIStates
import com.andrade.dennisse.proyectoandrade.ui.entities.rated.MoviesRatedInfoUI
import com.andrade.dennisse.proyectoandrade.ui.entities.rated.SeriesRatedInfoUI
import kotlinx.coroutines.launch

class ListarRatedSeriesInfoVM : ViewModel() {
    val itemsSeries = MutableLiveData<List<SeriesRatedInfoUI>>()
    val uiState = MutableLiveData<UIStates>()

    fun initData() {
        Log.d("TAG", "Ingresando al VM")
        viewModelScope.launch {
            uiState.postValue(UIStates.Loading(true))
            GetSeriesRatedUserCase().invoke().collect { respuesta ->
                respuesta.onSuccess { items ->
                    itemsSeries.postValue(items)
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
