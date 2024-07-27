package com.andrade.dennisse.proyectoandrade.logic.usercase.login

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class PasswordRecovery(private val context: Context) {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun invoke(email: String): Flow<Result<Unit>> = flow {
        try {
            // Enviar el correo de recuperación de contraseña
            firebaseAuth.sendPasswordResetEmail(email).await()
            // Emitir éxito si el correo se envió correctamente
            emit(Result.success(Unit))
        } catch (e: Exception) {
            // Emitir error si algo salió mal
            emit(Result.failure(e))
        }
    }
}
