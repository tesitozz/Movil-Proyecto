package com.example.appproyecto

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var txtUsuario : TextInputEditText
    private lateinit var txtClave : TextInputEditText
    private lateinit var btnLogin : Button
    private lateinit var btnFacebook : LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtUsuario = findViewById(R.id.txtUsuario)
        txtClave = findViewById(R.id.txtClave)
        btnLogin = findViewById(R.id.btnLogin)
        btnFacebook = findViewById(R.id.btnFacebook)
        btnLogin.setOnClickListener { login() }
        btnFacebook.setOnClickListener { loginFacebook() }
    }

    fun login() {
        var usuario : String
        var clave : String

        usuario = txtUsuario.text.toString()
        clave = txtClave.text.toString()
        showAlert("Usuario es : " + usuario + "\n" + "Clave es : " + clave)
    }

    fun loginFacebook() {
        var texto : String = "Usted inicio sesión con facebook"
        showAlert(texto)
    }

    fun showAlert(men:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Login")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }
}