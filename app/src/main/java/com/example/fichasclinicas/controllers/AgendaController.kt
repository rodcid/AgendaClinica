package com.example.fichasclinicas.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.room.PrimaryKey
import androidx.room.Room
import com.example.fichasclinicas.MainActivity
import com.example.fichasclinicas.lib.AppDatabase
import com.example.fichasclinicas.miCuenteActivity
import com.example.fichasclinicas.models.Agenda
import com.example.fichasclinicas.models.AgendaEntity

class AgendaController constructor(ctx : Context, userId: Long = 0 ) {
    private val ctx = ctx
    private val sharedPref = ctx.getSharedPreferences("AGENDA_CLINICA", Context.MODE_PRIVATE)
    private val userId = userId
    private val dao = Room
        .databaseBuilder(ctx, AppDatabase::class.java, "database-name")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .agendaDao()

    fun getAll(): List<Agenda> {
        val agendaEntities = dao.findAll(userId)
        val agendas = ArrayList<Agenda>()

        agendaEntities.forEach { agenda -> agendas.add(Agenda(
            id = agenda.id,
            paciente = agenda.paciente,
            medico = agenda.medico,
            hora = agenda.hora,
            examen = agenda.examen,
            observacion = agenda.observacion,
            estado = agenda.estado,
            activo = agenda.activo,
            box = agenda.box
        )) }
        return agendas
    }

    fun create(agenda : Agenda){
        val entity = AgendaEntity(
            id = null,
            paciente = agenda.paciente,
            medico = agenda.medico,
            hora = agenda.hora,
            examen = agenda.examen,
            observacion = agenda.observacion,
            estado = agenda.estado,
            activo  = agenda.activo,
            box = agenda.box,
            userId = sharedPref.getLong("user_id", -1)
        )
        dao.insert(entity)
        val intent = Intent(ctx,MainActivity::class.java)
        ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }

    fun update(agenda : Agenda){
        val entity = AgendaEntity(
            id = null,
            paciente = agenda.paciente,
            medico = agenda.medico,
            hora = agenda.hora,
            examen = agenda.examen,
            observacion = agenda.observacion,
            estado = agenda.estado,
            activo  = agenda.activo,
            box = agenda.box,
            userId = sharedPref.getLong("user_id", -1)
        )
        dao.update(entity)
        val intent = Intent(ctx,MainActivity::class.java)
        ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }

    fun delete(id : Long){
        dao.delete(id)
        val intent = Intent(ctx,MainActivity::class.java)
        ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }


}