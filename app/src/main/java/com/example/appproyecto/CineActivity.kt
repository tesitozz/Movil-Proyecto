package com.example.appproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.time.times

class CineActivity : AppCompatActivity() {

    private lateinit var txtCliente : TextInputEditText
    private lateinit var rbtnDramatica : RadioButton
    private lateinit var rbtnInfantiles : RadioButton
    private lateinit var spnPelicula : AutoCompleteTextView
    private lateinit var txtNinos : TextInputEditText
    private lateinit var txtAdultos : TextInputEditText
    private lateinit var btnCompra : Button

    var posGenero = -1
    var posPelicula = -1

    var costoNinos : Double = 0.0
    var costoAdultos : Double = 0.0
    var montoNinos : Double = 0.0
    var montoAdultos : Double = 0.0
    var totalPagar : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cine_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtCliente = findViewById(R.id.txtCliente)
        rbtnDramatica = findViewById(R.id.rbtnDramatica)
        rbtnInfantiles = findViewById(R.id.rbtnInfantiles)
        spnPelicula = findViewById(R.id.spnPelicula)
        txtNinos = findViewById(R.id.txtNinos)
        txtAdultos = findViewById(R.id.txtAdultos)
        btnCompra = findViewById(R.id.btnCompra)

        btnCompra.setOnClickListener { compra() }

        rbtnDramatica.setOnClickListener { dramatica() }
        rbtnInfantiles.setOnClickListener { infantiles() }
    }

    fun dramatica() {

        //Arreglo
        val peliculas = arrayOf("Lo imposible", "12 años de esclavitud", "Historias cruzadas")

        //Adaptador
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, peliculas)

        //Asignar objeto adaptador al atributo spnPelicula
        spnPelicula.setAdapter(adaptador)

        // Listener para el AutoCompleteTextView para obtener la posición del elemento seleccionado
        spnPelicula.setOnItemClickListener { parent, view, position, id ->
            posPelicula = position
        }

        //Asignar Genero
        posGenero = 0
    }

    fun infantiles() {

        //Arreglo
        val peliculas = arrayOf("Alvin y las ardillas", "Arthur y los Minimoys", "Bolt", "Cars", "Encantada")

        //Adaptador
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, peliculas)

        //Asignar objeto adaptador al atributo spnPelicula
        spnPelicula.setAdapter(adaptador)

        // Listener para el AutoCompleteTextView para obtener la posición del elemento seleccionado
        spnPelicula.setOnItemClickListener { parent, view, position, id ->
            posPelicula = position
        }

        //Asignar Genero
        posGenero = 1
    }

    fun compra() {

        if (posGenero == 0 && posPelicula == 0) {
            costoAdultos = 30.5
            costoNinos = costoAdultos - 10
        }
        else if (posGenero == 0 && posPelicula == 1) {
            costoAdultos = 28.3
            costoNinos = costoAdultos - 10
        }
        else if (posGenero == 0 && posPelicula == 2) {
            costoAdultos = 25.5
            costoNinos = costoAdultos - 10
        }
        else if (posGenero == 1 && posPelicula == 0) {
            costoAdultos = 58.9
            costoNinos = costoAdultos - 10
        }
        else if (posGenero == 1 && posPelicula == 1) {
            costoAdultos = 57.0
            costoNinos = costoAdultos - 10
        }
        else if (posGenero == 1 && posPelicula == 2) {
            costoAdultos = 60.0
            costoNinos = costoAdultos - 10
        }
        else if (posGenero == 1 && posPelicula == 3) {
            costoAdultos = 65.5
            costoNinos = costoAdultos - 10
        }
        else if (posGenero == 1 && posPelicula == 4) {
            costoAdultos = 57.8
            costoNinos = costoAdultos - 10
        }

        val cantidadNinosString = txtNinos.text.toString()
        val cantidadNinos = cantidadNinosString.toDoubleOrNull()

        val cantidadAdultoString = txtAdultos.text.toString()
        val cantidadAdultos = cantidadAdultoString.toDoubleOrNull()

        if (cantidadNinos != null) {
            // Realizar la multiplicación
            montoNinos = costoNinos * cantidadNinos
        } else {
            montoNinos = 0.0
        }

        if (cantidadAdultos != null) {
            // Realizar la multiplicación
            montoAdultos = costoNinos * cantidadAdultos
        } else {
            montoAdultos = 0.0
        }

        totalPagar = montoNinos + montoAdultos

        var data = Intent(this, CompraActivity::class.java)
        data.putExtra("cliente", txtCliente.text.toString())
        data.putExtra("asientosNinos", txtNinos.text.toString())
        data.putExtra("asientosAdultos", txtAdultos.text.toString())
        data.putExtra("montoNinos", montoNinos)
        data.putExtra("montoAdultos", montoAdultos)
        data.putExtra("totalPagar", totalPagar)
        data.putExtra("pGenero", posGenero)
        data.putExtra("pPelicula", posPelicula)

        startActivity(data)
    }
}