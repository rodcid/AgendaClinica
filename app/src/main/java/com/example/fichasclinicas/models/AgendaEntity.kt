package com.example.fichasclinicas.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agenda")
data class AgendaEntity (
    @PrimaryKey(autoGenerate = true)val id : Long?,
    val paciente : String,
    val medico : String,
    val hora : String,
    val examen : String,
    val observacion : String,
    val estado : String,
    val activo : Boolean,
    val box : Int
    )
