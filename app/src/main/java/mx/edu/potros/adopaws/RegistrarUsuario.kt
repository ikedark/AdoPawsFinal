package mx.edu.potros.adopaws

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import mx.edu.potros.adopaws.databinding.ActivityRegistrarUsuarioBinding

class RegistrarUsuario : AppCompatActivity() {
    var fechaS: String = ""
    lateinit var eDate: EditText

    private lateinit var binding: ActivityRegistrarUsuarioBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnInicioSesion : Button = findViewById(R.id.btn_iniciarSesión)

        btnInicioSesion.setOnClickListener {
            val intent: Intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        eDate  = findViewById(R.id.et_Date)
        eDate.setOnClickListener { showDatePickerDialog() }

        binding.btnCrearCuenta.setOnClickListener(){
            val nomusuario = binding.etUsuario.text.toString()
            val fechanac = binding.etDate.text.toString()
            val contrasenia = binding.etPassword.text.toString()
            val correo = binding.etEmail.text.toString()



            databaseReference = FirebaseDatabase.getInstance().getReference("usuarios")
            val users = Usuarios(nomusuario, fechanac, correo, contrasenia)
            databaseReference.child(nomusuario).setValue(users).addOnSuccessListener {
                binding.etUsuario.text.clear()
                binding.etDate.text.clear()
                binding.etEmail.text.clear()
                binding.etPassword.text.clear()

                Toast.makeText(this, "Registro con éxito", Toast.LENGTH_SHORT).show()

                val intent = Intent (this@RegistrarUsuario, Login::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Falló el registro", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragmentRegistroU { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year:Int){
        eDate.setText("$day/$month/$year")
    }
}