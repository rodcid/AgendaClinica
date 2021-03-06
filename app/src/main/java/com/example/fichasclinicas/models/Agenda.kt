package com.example.fichasclinicas.models

import java.io.Serializable

data class Agenda(
    val id : Long?,
    val paciente : String,
    val medico : String,
    val hora : String,
    val examen : String,
    val observacion : String?,
    val estado : String,
    val activo : Boolean,
    val box : Int
) : Serializable
