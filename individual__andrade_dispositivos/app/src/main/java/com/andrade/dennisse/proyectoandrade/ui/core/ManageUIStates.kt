package com.andrade.dennisse.proyectoandrade.ui.core

import android.content.Context
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder


//ESTA CLASE ES GENERICA Y ME SIRVE PARA TODAS
class ManageUIStates(private val context: Context, private val view: View) {

    fun invoke(state: UIStates) {
        when (state) {
            is UIStates.Loading -> {
                if (state.isLoading) {
                    view.visibility = View.VISIBLE
                } else {
                    view.visibility = View.GONE
                }
            }
            is UIStates.Error -> {
                MaterialAlertDialogBuilder(context)
                    .setTitle("Error")
                    .setMessage(state.message)
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
            is UIStates.Success -> {
                MaterialAlertDialogBuilder(context)
                    .setTitle("¡Éxito!")
                    .setMessage("La operación fue exitosa.")
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}
