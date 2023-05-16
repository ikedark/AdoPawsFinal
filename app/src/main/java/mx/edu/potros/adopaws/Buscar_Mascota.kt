package mx.edu.potros.adopaws

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Buscar_Mascota : AppCompatActivity() {
    private lateinit var myRef : DatabaseReference
    private lateinit var mascotasRecyclerView : RecyclerView
    private lateinit var mascotasArrayList : ArrayList<mascotaR>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_mascota)

        val buttonEncontrados: Button = findViewById(R.id.btn_encontrados)

        val btnAyuda: ImageButton = findViewById(R.id.btn_ayuda)

        btnAyuda.setOnClickListener {
            val builder = AlertDialog.Builder(this@Buscar_Mascota)

            val view = layoutInflater.inflate(R.layout.dialog_reporte, null)

            builder.setView(view)

            val dialog = builder.create()

            dialog.show()

            val btnEncontre : Button? = dialog.findViewById(R.id.btnEncontre)

            val btnPerdi: Button? = dialog.findViewById(R.id.btnPerdi)

            if (btnPerdi != null) {
                btnPerdi.setOnClickListener {
                    val intent: Intent = Intent(this, reportePerdido::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }
            }

            if (btnEncontre != null) {
                btnEncontre.setOnClickListener {
//                    finish()
                    val intent: Intent = Intent(this, reporteEncontrado::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }
            }
        }

        buttonEncontrados.setOnClickListener(){
            var intent: Intent = Intent(this,Encontrar_Mascota::class.java)
            startActivity(intent)
        }


        val btnHome: ImageButton = findViewById(R.id.btn_home)
        val btnMapa: ImageButton = findViewById(R.id.btn_map)
        val btnAvisos: ImageButton = findViewById(R.id.btn_warnings)
        val btnMensajes: ImageButton = findViewById(R.id.btn_messages)
        val btnProfile: ImageButton = findViewById(R.id.btn_profile)

        btnHome.setOnClickListener {
            val intent: Intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        btnMensajes.setOnClickListener {
            val intent: Intent = Intent(this, conversaciones::class.java)
            startActivity(intent)
        }

        btnAvisos.setOnClickListener {
            val intent: Intent = Intent(this, Encontrar_Mascota::class.java)
            startActivity(intent)
        }

        btnProfile.setOnClickListener {
            val intent: Intent = Intent(this, perfilUsuario::class.java)
            startActivity(intent)
        }

        btnMapa.setOnClickListener {
            val intent: Intent = Intent(this, MapaPrueba::class.java)
            startActivity(intent)
        }

    }

    private fun getUserData() {
        myRef = FirebaseDatabase.getInstance().getReference("mascotaEncontrada")
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (mascotasSnapshot in snapshot.children){
                        val mascota = mascotasSnapshot.getValue(mascotaR::class.java)
                        mascotasArrayList.add(mascota!!)
                    }
                    mascotasRecyclerView.adapter = mascotasAdapter(mascotasArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}