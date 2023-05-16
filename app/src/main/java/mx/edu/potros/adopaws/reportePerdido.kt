package mx.edu.potros.adopaws

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import mx.edu.potros.adopaws.databinding.ActivityReportePerdidoBinding
import java.io.File

class reportePerdido : AppCompatActivity() {
    private val mascotaRef = FirebaseDatabase.getInstance().getReference("mascotaPerdida")
    val storage = Firebase.database
    private lateinit var databaseReference: DatabaseReference
    private var imagen : String = ""
    private val File = 1
    lateinit var binding: ActivityReportePerdidoBinding
    lateinit var ImageUri: Uri
    lateinit var img_btn_upload: ImageView
    lateinit var et_Nombre : EditText
    lateinit var et_Desc : EditText
    lateinit var et_ubicacion : EditText
    lateinit var et_cel : EditText
    lateinit var fechaR : EditText
    lateinit var s_sexoM: Spinner
    lateinit var btnReportar: Button
    var sexoPet : String = ""
    val mascotaId = mascotaRef.push().key!!
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportePerdidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        et_Nombre  = findViewById(R.id.nombreP)
        et_Desc  = findViewById(R.id.descP)
        et_ubicacion = findViewById(R.id.ubicacionP)
        et_cel  = findViewById(R.id.celP)
        s_sexoM  = findViewById(R.id.spinnerSexo)
        btnReportar = findViewById(R.id.btnReportar)
        fechaR  = findViewById(R.id.fechaP)
        fechaR.setOnClickListener { showDatePickerDialog() }


        img_btn_upload = findViewById(R.id.btn_subirFoto)
        img_btn_upload.setOnClickListener {
            selectImage()
        }

        val lista = resources.getStringArray(R.array.sexoOpciones)
        val adaptador = ArrayAdapter(this, R.layout.spinner_sexo_mascota, lista)
        s_sexoM.adapter = adaptador

        s_sexoM.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sexoPet = lista[position].toString()
                //Toast.makeText(this@reportePerdido, lista[position], Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        btnReportar.setOnClickListener {
            savePetData()
            //uploadImagen()
            //val intent: Intent = Intent(this, Mascotas_EP::class.java)
        }
    }

    private fun savePetData(){
        val nombreMascota = et_Nombre.text.toString()
        val descripcionMascota = et_Desc.text.toString()
        val lugarExtravio = et_ubicacion.text.toString()
        val fechaExtravio = fechaR.text.toString()
        val telefonoDuenio = et_cel.text.toString()
        imagen = ImageUri.toString()

        if (nombreMascota.isNullOrEmpty() || descripcionMascota.isNullOrEmpty() || lugarExtravio.isNullOrEmpty() || telefonoDuenio.length > 10
            || telefonoDuenio.isNullOrEmpty() || fechaExtravio.isNullOrEmpty()){
            Toast.makeText(this, "Por favor llene los campos corectamente", Toast.LENGTH_LONG).show()
        }
        else{
            databaseReference = FirebaseDatabase.getInstance().getReference("/mascotaPerdida")
            val mascota : mascotaR = mascotaR(mascotaId, nombreMascota, descripcionMascota, fechaExtravio, telefonoDuenio, lugarExtravio, null, sexoPet)

            databaseReference.child(nombreMascota).setValue(mascota)
                .addOnCompleteListener {
                    val builder = AlertDialog.Builder(this@reportePerdido)

                    val view = layoutInflater.inflate(R.layout.reporte_creado, null)

                    builder.setView(view)

                    val dialog = builder.create()

                    dialog.show()

                    val btnAceptar: Button? = dialog.findViewById(R.id.btnAceptar)

                    btnAceptar?.setOnClickListener {
                        val intent: Intent = Intent(this, Encontrar_Mascota::class.java)
                        startActivity(intent)
                        dialog.dismiss()
                        Toast.makeText(this, "El reporte de $nombreMascota se genero correctamente", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }

        }
    }


    private fun uploadImagen(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Subiendo imagen")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val fileName = "${et_Nombre.text.toString()}Image"
        val imagenRef = FirebaseStorage.getInstance().getReference("mascotaPerdida/$fileName")
        imagenRef.child(mascotaId).putFile(ImageUri).
        addOnSuccessListener {
            binding.ivPet.setImageURI(null)
            Toast.makeText(this@reportePerdido, "Imagen subida correctamente", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()
        }. addOnFailureListener{
            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@reportePerdido, "Error", Toast.LENGTH_SHORT).show()
        }

    }

    private fun selectImage(){
        val intent = Intent()
        intent.type = "*/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK){
            ImageUri = data?.data!!
            binding.ivPet.setImageURI(ImageUri)
        }
    }


    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragmentMascotas { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year:Int){
        fechaR.setText("$day/$month/$year")
    }
}
