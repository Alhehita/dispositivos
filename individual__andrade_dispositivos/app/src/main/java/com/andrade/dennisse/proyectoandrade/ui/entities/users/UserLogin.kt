package com.andrade.dennisse.proyectoandrade.ui.entities.users

import java.util.Date


//BUSCAR Y VER SI EL OBJETO ES SERIALIZABLE..
//BLIBIOTEC PARA QEUSE PUEDA SERIALIZAR
data class UserLogin(
    val uuid: String = "",
    val name: String = "",
    val lastName: String = "",
    val email:String="",
    val pass : String=""




) {
//constructor():this("","","")
}