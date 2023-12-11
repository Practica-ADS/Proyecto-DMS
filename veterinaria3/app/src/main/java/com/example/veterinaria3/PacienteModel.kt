package com.example.veterinaria3

import java.util.*

data class PacienteModel(
    var id: Int = getAutoId(),
    var name: String = "",
    var raza: String = "",
    var encargado: String =""
){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }

}