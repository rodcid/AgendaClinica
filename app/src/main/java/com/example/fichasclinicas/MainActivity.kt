package com.example.fichasclinicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TableLayout
import com.example.fichasclinicas.controllers.AgendaController
import com.example.fichasclinicas.ui.AgendaAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val lvagenda = findViewById<ListView>(R.id.activity_main_lv_agenda)
        val allAgenda = AgendaController(this).getAll()
        val adapter = AgendaAdapter(this,allAgenda)

        lvagenda.adapter = adapter

        lvagenda.setOnItemClickListener { adapterView, view, i, l ->
            run{
                val agenda = allAgenda[i]
                val intent = Intent(view.context, AgendaItemActivity::class.java)
                intent.putExtra("agenda", agenda)
                view.context.startActivity(intent)
            }
        }

        val btnAgregarpaciente = findViewById<Button>(R.id.activity_mainactivity_btn_agregarpaciete)

        btnAgregarpaciente.setOnClickListener{
            val intent = Intent(this, AgregarPaciente::class.java)
            startActivity(intent)
        }
    }
}