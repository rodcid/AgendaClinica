package com.example.fichasclinicas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.fichasclinicas.controllers.AuthController
import com.example.fichasclinicas.utils.TilValidador
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val btnRegistrar = findViewById<Button>(R.id.activity_login_btn_crearcuenta)    //se captura el boton Registrar
        val btnIngresar = findViewById<Button>(R.id.activity_login_btn_iniciarsesion)   //se captura el boton ingresar
        val tilEmail = findViewById<TextInputLayout>(R.id.activity_login_til_user)      // se captura el til email
        val tilPass = findViewById<TextInputLayout>(R.id.activity_login_til_pass)       // se captura el til de la pass

        //luego se trabaja en el proceso al clickear el boton.
        btnIngresar.setOnClickListener{
            val email = tilEmail.editText?.text.toString()  //se inicializa el tilemail
            val pass = tilPass.editText?.text.toString()    //se inicializa el tilpass

            val emailValid =TilValidador(tilEmail).required().email().isValid();    //se realiza la validacion del campo email
            val passValid = TilValidador(tilPass).required().isValid()              //se realiza la validacion del campo pass

            if(emailValid && passValid){                        //si emailvaid y passvalid son true
                AuthController(this).login(email,pass)      //se va al auth controller a comprobar si las credenciales existen en la BD
            }else{
                Toast.makeText(this,"campos invalidos",Toast.LENGTH_SHORT).show()
            }
        }
        btnRegistrar.setOnClickListener{
            val intent = Intent(this , CrearCuentaActivity::class.java)
            startActivity(intent)
        }
    }
}