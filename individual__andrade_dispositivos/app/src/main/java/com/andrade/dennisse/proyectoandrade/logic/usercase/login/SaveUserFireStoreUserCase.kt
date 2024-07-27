package com.andrade.dennisse.proyectoandrade.logic.usercase.login

import com.andrade.dennisse.proyectoandrade.data.firebase.FirestoreRepository
import com.andrade.dennisse.proyectoandrade.ui.entities.users.UserLogin
import kotlinx.coroutines.flow.flow

class SaveUserFireStoreUserCase() {

  //  val userUI

    suspend fun invoke(user: UserLogin) = flow {
        val x = FirestoreRepository().saveUserLogin(user)
        x.onSuccess {
            emit(it)
        }
        x.onFailure {
            emit(false)

        }
    }
}
