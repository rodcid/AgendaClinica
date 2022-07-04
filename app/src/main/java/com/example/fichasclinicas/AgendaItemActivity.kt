package com.example.fichasclinicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.fichasclinicas.controllers.AgendaController
import com.example.fichasclinicas.controllers.AuthController
import com.example.fichasclinicas.models.Agenda
import org.w3c.dom.Text

class AgendaItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda_item)

        val authController = AuthController(this)
        val agendaController = AgendaController(this,authController.getSessionUserId() )

        val agenda = intent.getSerializableExtra("agenda") as Agenda

        val tvHora = findViewById<TextView>(R.id.activity_agenda_item_tv_hora)
        val tvPaciente = findViewById<TextView>(R.id.activity_agenda_item_tv_paciente)
        val tvMedico = findViewById<TextView>(R.id.activity_agenda_item_tv_medico)
        val tvExamen = findViewById<TextView>(R.id.activity_agenda_item_tv_examen)
        val tvObservacion = findViewById<TextView>(R.id.activity_agenda_item_tv_observacion)
        val btnUpdate = findViewById<Button>(R.id.activity_agenda_iten_btn_updatePaciente)
        val btnDelete = findViewById<Button>(R.id.activity_agenda_iten_btn_eliminarPaciente)

        tvHora.text = agenda.hora
        tvPaciente.text = agenda.paciente
        tvMedico.text = agenda.medico
        tvExamen.text = agenda.examen
        tvObservacion.text = agenda.observacion

        btnUpdate.setOnClickListener{
            val intent = Intent(this, AgregarPaciente::class.java)
            intent.putExtra("agenda", agenda)
            startActivity(intent)
        }

        btnDelete.setOnClickListener{
            agendaController.delete(agenda.id!!)
        }
    }
}