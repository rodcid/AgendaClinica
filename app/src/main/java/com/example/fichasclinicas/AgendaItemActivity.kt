package com.example.fichasclinicas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.fichasclinicas.models.Agenda
import org.w3c.dom.Text

class AgendaItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda_item)

        val agenda = intent.getSerializableExtra("agenda") as Agenda

        val tvHora = findViewById<TextView>(R.id.activity_agenda_item_tv_hora)
        val tvPaciente = findViewById<TextView>(R.id.activity_agenda_item_tv_paciente)
        val tvMedico = findViewById<TextView>(R.id.activity_agenda_item_tv_medico)
        val tvExamen = findViewById<TextView>(R.id.activity_agenda_item_tv_examen)
        val tvObservacion = findViewById<TextView>(R.id.activity_agenda_item_tv_observacion)

        tvHora.text = agenda.hora
        tvPaciente.text = agenda.paciente
        tvMedico.text = agenda.medico
        tvExamen.text = agenda.examen
        tvObservacion.text = agenda.observacion
    }
}