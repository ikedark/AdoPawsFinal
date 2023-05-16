package mx.edu.potros.adopaws

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Historia_Mascota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historia_mascota)

        val button: Button = findViewById(R.id.btn_perfil)
        val button2: Button = findViewById(R.id.btn_salud)
        val button3: Button = findViewById(R.id.btn_contacto)

        val buttonR: ImageButton = findViewById(R.id.btn_regresar)


        buttonR.setOnClickListener(){
            var intent: Intent = Intent(this,Home::class.java)
            startActivity(intent)
        }

        button.setOnClickListener(){
            var intent: Intent = Intent(this,Perfil_Mascota::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener(){
            var intent: Intent = Intent(this,Salud_Mascota::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener(){
            var intent: Intent = Intent(this,Contacto_Mascota::class.java)
            startActivity(intent)
        }
    }
}