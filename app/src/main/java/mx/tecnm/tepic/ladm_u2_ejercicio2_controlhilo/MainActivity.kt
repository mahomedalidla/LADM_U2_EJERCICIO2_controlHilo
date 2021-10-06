package mx.tecnm.tepic.ladm_u2_ejercicio2_controlhilo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hilo = Hilo(this)

        button.setOnClickListener {
            try {
                hilo.start()
            } catch (io:Exception){
                Toast.makeText(this,"Error el hilo ya habia sido ejecutado",Toast.LENGTH_LONG).show()
            }
        }

        button2.setOnClickListener {
            hilo.pausar()
        }

        button3.setOnClickListener {
            hilo.terminar()
        }
    }
}

class Hilo(p:MainActivity): Thread(){
    var contador = 1
    val puntero = p
    var pausado = false
    var contunuarCiclo = true

    fun pausar(){
        pausado = !pausado
    }

    fun terminar(){
        contunuarCiclo=false
    }

    override fun run(){
        super.run()
        while (contunuarCiclo){
            if (pausado==false) {
                puntero.runOnUiThread {
                    puntero.textView.text = "Valor hilo ${contador}"
                }
                contador++
            }
            sleep(200)
        }
    }

}