package com.example.fichasclinicas.controllers

import android.content.Context
import com.example.fichasclinicas.models.Agenda

class AgendaController constructor(ctx : Context) {
    private val ctx = ctx

    fun getAll(): List<Agenda>{
        val agendas = ArrayList<Agenda>()
        for (i in 1..10){
            agendas.add(Agenda(
                id = i.toLong(),
                paciente = "Paciente $i",
                medico = "Medico $i",
                hora = "11:00",
                examen = "Endoscopia",
                estado = "en procedimiento",
                activo = true
            ))
        }
        return agendas
    }
}