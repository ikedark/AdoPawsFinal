package mx.edu.potros.adopaws

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class conversaciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversaciones)

        val btnHome: ImageButton = findViewById(R.id.btn_home)
        val btnMapa: ImageButton = findViewById(R.id.btn_map)
        val btnAvisos: ImageButton = findViewById(R.id.btn_warnings)
        val btnMensajes: ImageButton = findViewById(R.id.btn_messages)
        val btnProfile: ImageButton = findViewById(R.id.btn_profile)

        btnHome.setOnClickListener {
            var intent: Intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        btnProfile.setOnClickListener {
            var intent: Intent = Intent(this, perfilUsuario::class.java)
            startActivity(intent)
        }

        btnAvisos.setOnClickListener {
            var intent: Intent = Intent(this, Encontrar_Mascota::class.java)
            startActivity(intent)
        }

        btnMensajes.setOnClickListener {
            val intent: Intent = Intent(this, conversaciones::class.java)
            startActivity(intent)
        }

        val btnComunidades: Button = findViewById(R.id.btn_comunidades)
        val btnPrivados: Button = findViewById(R.id.btn_privados)

        btnComunidades.setOnClickListener {
            var intent: Intent = Intent(this, conversaciones::class.java)
            intent.putExtra("tipoMascota","Adopcion")
            startActivity(intent)
        }

        btnPrivados.setOnClickListener {
            var intent: Intent = Intent(this, conversaciones::class.java)
            intent.putExtra("tipoMascota","Interes")
            startActivity(intent)
        }


    }
}