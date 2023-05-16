package mx.edu.potros.adopaws

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class Buscar_Mascota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_mascota)

        val buttonEncontrados: Button = findViewById(R.id.btn_encontrados)
        val emergenteEspanto: ImageView = findViewById(R.id.iv_mascotaEspanto)
        val btnHome: ImageButton = findViewById(R.id.btn_home)

        buttonEncontrados.setOnClickListener(){
            var intent: Intent = Intent(this,Encontrar_Mascota::class.java)
            startActivity(intent)
        }

        emergenteEspanto.setOnClickListener(){
            var intent: Intent = Intent(this,Emergente_Espanto::class.java)
            startActivity(intent)
        }

        btnHome.setOnClickListener {
            val intent: Intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}