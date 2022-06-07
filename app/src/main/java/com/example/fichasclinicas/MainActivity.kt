package com.example.fichasclinicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import com.example.fichasclinicas.controllers.AgendaController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val allAgenda = AgendaController(this).getAll()
        val btnAgregarpaciente = findViewById<Button>(R.id.activity_mainactivity_btn_agregarpaciete)

        btnAgregarpaciente.setOnClickListener{
            val intent = Intent(this, AgregarPaciente::class.java)
            startActivity(intent)
        }
    }
}