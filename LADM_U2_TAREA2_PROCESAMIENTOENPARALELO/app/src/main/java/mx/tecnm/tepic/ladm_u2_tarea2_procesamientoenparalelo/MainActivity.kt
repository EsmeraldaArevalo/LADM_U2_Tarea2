package mx.tecnm.tepic.ladm_u2_tarea2_procesamientoenparalelo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var hiloControl=Hilo(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageButton1.setOnClickListener {
            hiloControl?.start()
        }

        imageButton2.setOnClickListener {
            hiloControl?.pausar()
        }

        imageButton3.setOnClickListener {
            hiloControl?.despausar()
        }

        imageButton4.setOnClickListener {
            hiloControl!!.detener()

        }

        imageButton5.setOnClickListener {
            hiloControl!!.reiniciar()
        }
    }
}

class Hilo(p:MainActivity) : Thread(){
    var iniciar = false
    var puntero = p
    var pausa = false
    var contador = 0
    val lista = arrayOf("PERRO","GATO", "TORTUGA","CONEJO","POLLO","PATO","PEZ")
    override fun run() {
        super.run()
        iniciar = true
        while (iniciar==true){
            sleep(900)
            if(pausa==false) {
                puntero.runOnUiThread {
                    puntero.textView.setText(lista[contador]).toString()
                    contador++
                    if (contador==7){contador=0}
                }
            }
        }
    }

    fun estaIniciado():Boolean{
        return iniciar
    }

    fun pausar(){
        pausa= true
    }

    fun despausar(){
        pausa = false
    }

    fun detener() {
        iniciar = false
    }
    fun reiniciar(){
        contador=0
    }
}