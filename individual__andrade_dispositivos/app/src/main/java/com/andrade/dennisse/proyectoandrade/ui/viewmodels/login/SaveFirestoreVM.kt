package com.andrade.dennisse.proyectoandrade.ui.viewmodels.login

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrade.dennisse.proyectoandrade.logic.usercase.login.GetUserByEmailAndPasswordFBUC
import com.andrade.dennisse.proyectoandrade.logic.usercase.login.SaveUserFireStoreUserCase
import com.andrade.dennisse.proyectoandrade.logic.usercase.login.UpdateUserFireStoreUserCase
import com.andrade.dennisse.proyectoandrade.ui.core.UIStates
import com.andrade.dennisse.proyectoandrade.ui.entities.users.UserLogin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth

class SaveFirestoreVM : ViewModel() {

    val userUI get() = _userUI

    private var _userUI = MutableLiveData<UIStates>()

    val userLogin get() = _userLogin

    private var _userLogin = MutableLiveData<UserLogin?>()

    fun saveUserFirestore(user: UserLogin) {
        viewModelScope.launch {
            SaveUserFireStoreUserCase().invoke(user)
                .collect {
                    if (it) {
                        _userUI.postValue(UIStates.Success(true))
                    } else {
                        _userUI.postValue(
                            UIStates.Error("Ocurrió un error al generar la petición. Inténtelo de nuevo.")
                        )
                    }
                }
        }
    }

    fun getUserByIdFireStore(id: String, email: String, password: String) {
        viewModelScope.launch {
            _userUI.postValue(UIStates.Loading(true))
            GetUserByEmailAndPasswordFBUC().invoke(id)
                .collect { resp ->
                    resp.onSuccess { user ->
                        _userLogin.postValue(user)
                    }.onFailure {
                        // Si es un usuario nuevo, usar el email y la contraseña proporcionados
                        val newUser = UserLogin(email = email, pass = password)
                        _userLogin.postValue(newUser)
                    }
                }
            delay(500)
            _userUI.postValue(UIStates.Loading(false))
        }
    }

    fun updateUserFirestore(user: UserLogin) {
        viewModelScope.launch {
            _userUI.postValue(UIStates.Loading(true))
            val success = UpdateUserFireStoreUserCase().invoke(user)
            if (success) {
                _userUI.postValue(UIStates.Success(true))
            } else {
                _userUI.postValue(UIStates.Error("Ocurrió un error al actualizar los datos. Inténtelo de nuevo."))
            }
            delay(500)
            _userUI.postValue(UIStates.Loading(false))
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                // Limpiar la información local del usuario

                // Notificar que el cierre de sesión fue exitoso
                _userUI.postValue(UIStates.Success(true))
            } catch (e: Exception) {
                _userUI.postValue(UIStates.Error("Ocurrió un error al cerrar sesión. Inténtelo de nuevo."))
            }
        }
    }
}
