package com.example.fichasclinicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.fichasclinicas.controllers.AuthController
import com.example.fichasclinicas.utils.TilValidador
import com.example.fichasclinicas.utils.showDatePickerDialog
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class CrearCuentaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_cuenta_activity)

        //capturar los botones
        val btnCrearCuenta = findViewById<Button>(R.id.activity_crearcuenta_btn_registrar)
        val btnCancelar = findViewById<Button>(R.id.activity_crearcuenta_btn_cancelar)
        // capturar los textinputlayout
        val tilNombre = findViewById<TextInputLayout>(R.id.activity_crearcuenta_til_nombreuser)
        val tilApellido = findViewById<TextInputLayout>(R.id.activity_crearcuenta_til_apellidouser)
        val tilBirth = findViewById<TextInputLayout>(R.id.activity_crearcuenta_til_birthdate)
        val tilEmail = findViewById<TextInputLayout>(R.id.activity_crearcuenta_til_emailuser)
        val tilClave = findViewById<TextInputLayout>(R.id.activity_crearcuenta_til_clave)

        tilBirth.editText?.setOnClickListener { _ ->
            showDatePickerDialog(this, tilBirth, Date())
        }

        btnCrearCuenta.setOnClickListener {
            val nombre = tilNombre.editText?.text
            val apellido = tilApellido.editText?.text
            val birthdate = tilBirth.editText?.text.toString()
            val email = tilEmail.editText?.text
            val clave = tilClave.editText?.text

            val nombrevalido = TilValidador(tilNombre).required().isValid()
            val apellidovalido = TilValidador(tilApellido).required().isValid()
            val birthdatevalido = TilValidador(tilBirth).dateAfter(Date()).required().isValid()
            val emailvalido = TilValidador(tilEmail).email().required().isValid()
            val clavevalida = TilValidador(tilClave).required().isValid()


            if(nombrevalido && apellidovalido && birthdatevalido && emailvalido && clavevalida){
                AuthController(this)
                val intent = Intent(this, ConfirmarCrearCuentaActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Campos invalidos",Toast.LENGTH_SHORT).show()
            }
        }

        btnCancelar.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}