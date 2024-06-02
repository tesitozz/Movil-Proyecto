package com.example.appproyecto

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CompraActivity : AppCompatActivity() {

    private lateinit var imgFoto : ImageView
    private lateinit var tvClienteC : TextView
    private lateinit var tvGeneroC : TextView
    private lateinit var tvPeliculaC : TextView
    private lateinit var tvNinosC : TextView
    private lateinit var tvAdultosC : TextView
    private lateinit var tvMontoNinosC : TextView
    private lateinit var tvMontoAdultosC : TextView
    private lateinit var tvTotalPagarC : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.compra_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgFoto = findViewById(R.id.imgFoto)
        datos()
    }

    fun datos() {
        var info = intent.extras!!

        var pGene = info.getInt("pGenero")
        var pPeli = info.getInt("pPelicula")

        //variable ID para la pelicula
        var idPelicula = 0

        //Obtener el ID de la pelicula que empieza siempre con "p"
        idPelicula = this.resources.getIdentifier("p" + pGene + pPeli, "drawable", this.packageName)

        //mostrar ID en el atributo "imgFoto"
        imgFoto.setImageResource(idPelicula)

        //mostrar los otros datos
        tvClienteC.setText("CLIENTE: " + info.getString("cliente"))
        tvNinosC.setText("ASIENTO NIÑOS: " + info.getDouble("asientosNinos"))
        tvNinosC.setText("ASIENTO ADULTOS: " + info.getDouble("asientosAdultos"))
        tvNinosC.setText("MONTO NIÑOS: " + info.getDouble("montoNinos"))
        tvNinosC.setText("MONTO ADULTOS: " + info.getDouble("montoAdultos"))
        tvNinosC.setText("TOTAL A PAGAR: " + info.getDouble("totalPagar"))
    }
}