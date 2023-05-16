package mx.edu.potros.adopaws

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Perfil_Mascota : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_mascota)

        val button: Button = findViewById(R.id.btn_historia)
        val button2: Button = findViewById(R.id.btn_salud)
        val button3: Button = findViewById(R.id.btn_contacto)

        val buttonR: ImageButton = findViewById(R.id.btn_regresar)


        buttonR.setOnClickListener(){
            var intent: Intent =Intent(this,Home::class.java)
            startActivity(intent)
        }


        button.setOnClickListener(){
            var intent: Intent =Intent(this,Historia_Mascota::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener(){
            var intent: Intent =Intent(this,Salud_Mascota::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener(){
            var intent: Intent =Intent(this,Contacto_Mascota::class.java)
            startActivity(intent)
        }


        val iv_perfilMascota: ImageView =findViewById(R.id.iv_perfilMascota)
        val tv_nombreMascota: TextView =findViewById(R.id.tv_nombreMascota)
        val tv_sexoMascota: TextView =findViewById(R.id.tv_sexoMascota)



        val et_name_pet: TextView = findViewById(R.id.et_name_pet)
        val tv_edad_pet: TextView = findViewById(R.id.tv_edad_pet)
        val tv_sexo_pet: TextView = findViewById(R.id.tv_sexo_pet)
        val tv_raza_pet: TextView = findViewById(R.id.tv_raza_pet)
        val tv_tamanio_pet: TextView = findViewById(R.id.tv_tamanio_pet)
        val tv_colores_pet: TextView = findViewById(R.id.tv_colores_pet)
        val tv_personalidad_pet: TextView = findViewById(R.id.tv_personalidad_pet)
        val tv_social_pet: TextView = findViewById(R.id.tv_social_pet)
        val iv_perfilMascota1: ImageView = findViewById(R.id.iv_perfilMascota1)
        val iv_perfilMascota2: ImageView = findViewById(R.id.iv_perfilMascota2)
        val iv_perfilMascota3: ImageView = findViewById(R.id.iv_perfilMascota3)
        val iv_perfilMascota4: ImageView = findViewById(R.id.iv_perfilMascota4)
        val iv_perfilMascota5: ImageView = findViewById(R.id.iv_perfilMascota5)
        val iv_perfilMascota6: ImageView = findViewById(R.id.iv_perfilMascota6)



        val bundle = intent.extras

        if(bundle !=null){
            iv_perfilMascota.setImageResource(bundle.getInt("imagen"))
            tv_nombreMascota.setText(bundle.getString("nombre"))
            tv_sexoMascota.setText(bundle.getString("sexo"))

            et_name_pet.setText(bundle.getString("nombre"))
            tv_edad_pet.setText(bundle.getString("edad"))
            tv_sexo_pet.setText(bundle.getString("sexo"))
            tv_raza_pet.setText(bundle.getString("raza"))
            tv_tamanio_pet.setText(bundle.getString("tamanio"))
            tv_colores_pet.setText(bundle.getString("colores"))
            tv_personalidad_pet.setText(bundle.getString("personalidad"))
            tv_social_pet.setText(bundle.getString("social"))
            iv_perfilMascota1.setImageResource(bundle.getInt("perfil1"))
            iv_perfilMascota2.setImageResource(bundle.getInt("perfil2"))
            iv_perfilMascota3.setImageResource(bundle.getInt("perfil3"))
            iv_perfilMascota4.setImageResource(bundle.getInt("perfil4"))
            iv_perfilMascota5.setImageResource(bundle.getInt("perfil5"))
            iv_perfilMascota6.setImageResource(bundle.getInt("perfil6"))
        }

    }
}