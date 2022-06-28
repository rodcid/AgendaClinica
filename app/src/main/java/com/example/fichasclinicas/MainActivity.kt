package com.example.fichasclinicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.fichasclinicas.controllers.AgendaController
import com.example.fichasclinicas.controllers.AuthController
import com.example.fichasclinicas.ui.AgendaAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val lvagenda = findViewById<ListView>(R.id.activity_main_lv_agenda)
        val spnBox = findViewById<Spinner>(R.id.activity_main_activity_spn_box)

        val allAgenda = AgendaController(this).getAll()
        val adapter = AgendaAdapter(this,allAgenda)

        lvagenda.adapter = adapter

        val spinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.box_array,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnBox.adapter = spinnerAdapter

        lvagenda.setOnItemClickListener { adapterView, view, i, l ->
            run{
                val agenda = allAgenda[i]
                val intent = Intent(view.context, AgendaItemActivity::class.java)
                intent.putExtra("agenda", agenda)
                view.context.startActivity(intent)
            }
        }



        val btnAgregarpaciente = findViewById<Button>(R.id.activity_mainactivity_btn_agregarpaciete)
        val btnLogout = findViewById<Button>(R.id.activity_mainactivity_btn_logout)

        btnAgregarpaciente.setOnClickListener{
            val intent = Intent(this, AgregarPaciente::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener{
            val controller = AuthController(this)
            controller.clearSession()
        }
    }
}