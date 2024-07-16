package com.andrade.dennisse.proyectoandrade.ui.viewmodels.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SaveFirestoreVM: ViewModel() {

    val userUI get() = _userUI

    private var _userUI = MutableLiveData<Boolean>()

    fun saveUserFirestore(){

    }

}