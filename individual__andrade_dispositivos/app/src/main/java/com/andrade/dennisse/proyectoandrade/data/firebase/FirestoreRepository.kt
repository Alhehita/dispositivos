package com.andrade.dennisse.proyectoandrade.data.firebase

import com.andrade.dennisse.proyectoandrade.ui.entities.users.UserLogin
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirestoreRepository {

    private var db: FirebaseFirestore = Firebase.firestore

    suspend fun saveUserLogin(user: UserLogin) = kotlin.runCatching {
        val id = db.collection("users").add(user).await().id

        return@runCatching true
    }
}