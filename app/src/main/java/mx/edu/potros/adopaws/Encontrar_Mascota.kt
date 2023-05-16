package mx.edu.potros.adopaws

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Encontrar_Mascota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encontrar_mascota)

        val buttonPerdidos: Button = findViewById(R.id.btn_perdidos)
        val emergenteGato: ImageView = findViewById(R.id.iv_mascotaGato)

        buttonPerdidos.setOnClickListener(){
            var intent: Intent = Intent(this,Buscar_Mascota::class.java)
            startActivity(intent)
        }

        emergenteGato.setOnClickListener(){
            var intent: Intent = Intent(this,Emergente_Gato::class.java)
            startActivity(intent)
        }

    }
}