package com.example.veterinaria3

import java.util.*

data class CitaModel(
    var id: Int = getAutoId(),
    var name: String = "",
    var fecha: String = "",
    var hora: String ="",
    var encargado: String =""
){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }

}