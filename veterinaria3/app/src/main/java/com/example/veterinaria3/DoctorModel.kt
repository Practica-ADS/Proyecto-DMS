package com.example.veterinaria3

import java.util.*

data class DoctorModel(
    var id: Int = getAutoId(),
    var name: String = "",
    var puesto: String = "",
    var area: String =""
){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }

}