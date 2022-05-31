package com.example.fichasclinicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val btnAgregarpaciente = findViewById<Button>(R.id.activity_mainactivity_btn_agregarpaciete)
        val tabla = findViewById<TableLayout>(R.id.activity_activitymain_tl_tabla)

        btnAgregarpaciente.setOnClickListener{
            val intent = Intent(this, AgregarPaciente::class.java)
            startActivity(intent)
        }

        tabla.setOnClickListener{
            val intent = Intent(this, ModificarPaciente::class.java)
            startActivity(intent)
        }
    }
}