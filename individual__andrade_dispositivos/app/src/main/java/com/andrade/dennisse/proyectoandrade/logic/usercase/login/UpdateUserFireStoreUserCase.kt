package com.andrade.dennisse.proyectoandrade.logic.usercase.login

import com.andrade.dennisse.proyectoandrade.ui.entities.users.UserLogin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UpdateUserFireStoreUserCase {

    private lateinit var auth: FirebaseAuth

    suspend fun invoke(user: UserLogin): Boolean {
        auth = Firebase.auth

        return try {
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(auth.currentUser!!.uid)
                .set(user)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
