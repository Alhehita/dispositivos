package com.andrade.dennisse.proyectoandrade.data.firebase

import android.util.Log
import com.andrade.dennisse.proyectoandrade.ui.entities.users.UserLogin
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class FirestoreRepository {

    private var db: FirebaseFirestore = Firebase.firestore

    suspend fun saveUserLogin(user: UserLogin) = kotlin.runCatching {
        val id = db.collection("users")
            .add(user).await().id

        return@runCatching true
    }

    suspend fun getUserById(id: String)= runCatching{
        var items = arrayListOf<UserLogin>()

        db.collection("users")
            .whereEqualTo("uuid", id)//Me devolveria un valor igual al anterior
            .get()
            .await()
            .forEach {
                items.add(it.toObject<UserLogin>()) //JSON  de FIREBASE me lo pasa a DATA Class
        }

        return@runCatching items
    }
}