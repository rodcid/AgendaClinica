package com.example.fichasclinicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.*
import com.example.fichasclinicas.controllers.AgendaController
import com.example.fichasclinicas.controllers.AuthController
import com.example.fichasclinicas.models.Agenda
import com.example.fichasclinicas.utils.TilValidador
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

class AgregarPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_paciente)

        val authController =AuthController(this)
        val controller = AgendaController(this, authController.getSessionUserId() )
        val agenda = intent.getSerializableExtra("agenda") as Agenda?

        val tvTitle = findViewById<TextView>(R.id.activity_agregarpaciente_tv_titulo)
        val tilNombrePaciente = findViewById<TextInputLayout>(R.id.activity_agregarpaciente_til_nombrepaciente)
        val spnHora = findViewById<Spinner>(R.id.activity_agregar_paciente_spnHora)
        val tilMedico = findViewById<TextInputLayout>(R.id.activity_agregarpaciente_til_medico)
        val tilExamen = findViewById<TextInputLayout>(R.id.activity_agregarpaciente_til_examen)
        val tilObservacion = findViewById<TextInputLayout>(R.id.activity_agregarpaciente_til_observacion)

        if (agenda!= null){
            tvTitle.text ="Editar paciente"
            tilNombrePaciente.editText?.text = Editable.Factory.getInstance().newEditable(agenda.paciente)
            tilMedico.editText?.text = Editable.Factory.getInstance().newEditable(agenda.medico)
            tilExamen.editText?.text = Editable.Factory.getInstance().newEditable(agenda.examen)
            tilObservacion.editText?.text = Editable.Factory.getInstance().newEditable(agenda.observacion)
        }

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.horas_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnHora.adapter = adapter

        val btnAceptar = findViewById<Button>(R.id.activity_agregarpaciente_btn_agregarpaciente)
        val btnCancelar = findViewById<Button>(R.id.activity_agregarpaciente_btn_cancelaragregar)

        btnAceptar.setOnClickListener {
            val nombrePaciente = tilNombrePaciente.editText?.text.toString()
            val medico = tilMedico.editText?.text.toString()
            val examen = tilExamen.editText?.text.toString()
            val observacion = tilObservacion.editText?.text.toString()
            val hora = spnHora.selectedItem.toString()


            val nombrePacienteValido = TilValidador(tilNombrePaciente).required().isValid()
            val medicoValido = TilValidador(tilNombrePaciente).required().isValid()
            val examenValido = TilValidador(tilNombrePaciente).required().isValid()

            if (nombrePacienteValido && medicoValido && examenValido) {
                if (agenda == null) {
                    val newAgenda = Agenda(
                        id = null,
                        paciente = nombrePaciente,
                        medico = medico,
                        hora = hora,
                        examen = examen,
                        observacion = observacion,
                        estado = "Sin Registros",
                        activo = true,
                        box = 1
                    )
                    controller.create(newAgenda)
                    Toast.makeText(this, "Paciente agregado con exito", Toast.LENGTH_SHORT).show()

                } else {
                    val updatedAgenda = Agenda(
                        id = agenda.id,
                        paciente = nombrePaciente,
                        medico = medico,
                        hora = hora,
                        examen = examen,
                        observacion = observacion,
                        estado = "Sin Registros",
                        activo = true,
                        box = 1
                    )
                    controller.update(updatedAgenda)
                    Toast.makeText(this, "Paciente modificado con exito", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Campos inválidos", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancelar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}