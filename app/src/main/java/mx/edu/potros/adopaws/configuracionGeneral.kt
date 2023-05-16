package mx.edu.potros.adopaws

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog

class configuracionGeneral : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion_general)

        val btnCerrar : Button = findViewById(R.id.btn_cerrarSesión)
        val btnRegresar : ImageButton = findViewById(R.id.btn_Regresar)

        btnCerrar.setOnClickListener {
            val builder = AlertDialog.Builder(this@configuracionGeneral)

            val view = layoutInflater.inflate(R.layout.dialog_logout, null)

            builder.setView(view)

            val dialog = builder.create()

            dialog.show()

            val btnLogout : Button? = dialog.findViewById(R.id.btnLogout)

            val btnCancelar: Button? = dialog.findViewById(R.id.btnCancelar)

            if (btnCancelar != null) {
                btnCancelar.setOnClickListener {
                    dialog.dismiss()
                }
            }

            if (btnLogout != null) {
                btnLogout.setOnClickListener {
                    finish()
                    val intent: Intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }
            }

        }

        btnRegresar.setOnClickListener {
            val intent: Intent = Intent(this, perfilUsuario::class.java)
            startActivity(intent)
        }
    }
}