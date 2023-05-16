package mx.edu.potros.adopaws

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class perfilUsuario : AppCompatActivity() {

    var lista: ArrayList<Mascota> = ArrayList<Mascota>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)

        var tv_nombre: TextView = findViewById(R.id.tv_nombre_usuario)
        var iv_perfil: ImageView = findViewById(R.id.iv_perfil)

        var tipoMascota : String? = intent.getStringExtra("tipoMascota")

        agregarMascota(tipoMascota)
        var gridview: GridView = findViewById(R.id.gvPerfilUsuario) as GridView

        var adaptador: Home.AdaptadorMascotas = Home.AdaptadorMascotas(this, lista)
        gridview.adapter = adaptador

        val bundle = intent.extras

//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestEmail()
//            .build()

//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        if (bundle != null){
//            val profileImage = bundle.getInt("profileImage")
            val name = bundle.getString("name")
            tv_nombre.setText(name)
            //Picasso.with(context).load(profileImage).into(iv_perfil)
        }

        val btnHome: ImageButton = findViewById(R.id.btn_home)
        val btnMapa: ImageButton = findViewById(R.id.btn_map)
        val btnAvisos: ImageButton = findViewById(R.id.btn_warnings)
        val btnMensajes: ImageButton = findViewById(R.id.btn_messages)
        val btnProfile: ImageButton = findViewById(R.id.btn_profile)
        val btnConfig: ImageButton = findViewById(R.id.btn_Config)

        btnHome.setOnClickListener {
            var intent: Intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        btnMapa.setOnClickListener {
            val intent: Intent = Intent( this, MapaPrueba::class.java)
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

        btnConfig.setOnClickListener {
            var intent: Intent = Intent(this, configuracionGeneral::class.java)
            startActivity(intent)
        }

        val btnAdopcion: Button = findViewById(R.id.btn_mascota_adopcion)
        val btnInteres: Button = findViewById(R.id.btn_mascota_interes)

        btnAdopcion.setOnClickListener {
            var intent: Intent = Intent(this, perfilUsuario::class.java)
            intent.putExtra("tipoMascota","Adopcion")
            startActivity(intent)
        }

        btnInteres.setOnClickListener {
            var intent: Intent = Intent(this, perfilUsuario::class.java)
            intent.putExtra("tipoMascota","Interes")
            startActivity(intent)
        }
    }

    fun agregarMascota(option: String?) {
        when(option){
            "Interes" -> {
                lista.add(Mascota(R.drawable.canela,"CANELA", "Adulto, 3 años", "Hembra", "perro", "grande","Blanco con café","juguetón,enérgico,chipilon.","Amigable con los niños, perros y gatos","132",R.drawable.canela,R.drawable.dross2,R.drawable.dross3,R.drawable.dross4,R.drawable.dross5,R.drawable.dross6,"desde el principio de los tiempos"))
                lista.add(Mascota(R.drawable.demostenes,"DEMOSTENES", "Adulto, 3 años", "Macho", "gato", "mediano","Negro","juguetón,enérgico,chipilon.","Amigable con los niños, perros y gatos","421",R.drawable.demostenes,R.drawable.dross2,R.drawable.dross3,R.drawable.dross4,R.drawable.dross5,R.drawable.dross6,"desde el principio de los tiempos"))
                lista.add(Mascota(R.drawable.bethooven,"BEETHOVEN", "Adulto, 3 años", "Macho", "perro", "mediano","Negro con blanco y café","juguetón,enérgico,chipilon.","Amigable con los niños, perros y gatos","163",R.drawable.bethooven,R.drawable.dross2,R.drawable.dross3,R.drawable.dross4,R.drawable.dross5,R.drawable.dross6,"desde el principio de los tiempos"))
                lista.add(Mascota(R.drawable.gato_perdido,"MICHI", "Adulto, 3 años", "Hembra", "gato", "mediano","Anaranjado","juguetón,enérgico,chipilon.","Amigable con los niños, perros y gatos","512",R.drawable.dross1,R.drawable.dross2,R.drawable.dross3,R.drawable.dross4,R.drawable.dross5,R.drawable.dross6,"desde el principio de los tiempos"))
            }
            "Adopcion" -> {
                lista.add(Mascota(R.drawable.torpedo,"TORPEDO", "Adulto, 3 años", "Macho", "gato", "mediano","Blanco con café","juguetón,enérgico,chipilon.","Amigable con los niños, perros y gatos","162",R.drawable.canela,R.drawable.dross2,R.drawable.dross3,R.drawable.dross4,R.drawable.dross5,R.drawable.dross6,"desde el principio de los tiempos"))
                lista.add(Mascota(R.drawable.madonna,"MADONNA", "Adulto, 3 años", "Hembra", "gato", "mediano","Negro","juguetón,enérgico,chipilon.","Amigable con los niños, perros y gatos","199",R.drawable.demostenes,R.drawable.dross2,R.drawable.dross3,R.drawable.dross4,R.drawable.dross5,R.drawable.dross6,"desde el principio de los tiempos"))
                lista.add(Mascota(R.drawable.romina,"ROMINA", "Adulto, 3 años", "Hembra", "gato", "mediano","Negro con blanco y café","juguetón,enérgico,chipilon.","Amigable con los niños, perros y gatos","201",R.drawable.bethooven,R.drawable.dross2,R.drawable.dross3,R.drawable.dross4,R.drawable.dross5,R.drawable.dross6,"desde el principio de los tiempos"))
                lista.add(Mascota(R.drawable.benito,"BENITO", "Adulto, 3 años", "Macho", "perro", "chico","Anaranjado","juguetón,enérgico,chipilon.","Amigable con los niños, perros y gatos","94",R.drawable.dross1,R.drawable.dross2,R.drawable.dross3,R.drawable.dross4,R.drawable.dross5,R.drawable.dross6,"desde el principio de los tiempos"))

            }
        }


    }

    class AdaptadorMascotas: BaseAdapter {
        var mascotas = ArrayList<Mascota>()
        var contexto: Context? = null

        constructor(contexto: Context, mascota: ArrayList<Mascota>) {
            this.mascotas = mascota
            this.contexto = contexto
        }

        override fun getCount(): Int {
            return mascotas.size
        }

        override fun getItem(p0: Int): Any {
            return mascotas[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var masc = mascotas[p0]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.mascota_view, null)

            var imagen = vista.findViewById(R.id.mascota_img) as ImageView
            var nombre = vista.findViewById(R.id.nombre_mascota) as TextView
            var sexo = vista.findViewById(R.id.sexo_mascota) as TextView
            var likes = vista.findViewById(R.id.likes_mascota) as TextView


            imagen.setImageResource(masc.image)
            nombre.setText(masc.nombre)
            sexo.setText(masc.sexo)
            likes.setText(masc.likes)


            imagen.setOnClickListener() {
                var intento = Intent(contexto, Perfil_Mascota::class.java)
                intento.putExtra("imagen", masc.image)
                intento.putExtra("nombre", masc.nombre)
                intento.putExtra("sexo", masc.sexo)
                intento.putExtra("edad", masc.edad)
                intento.putExtra("raza", masc.raza)
                intento.putExtra("tamanio", masc.tamanio)
                intento.putExtra("colores", masc.colores)
                intento.putExtra("personalidad", masc.personalidad)
                intento.putExtra("social", masc.social)
                intento.putExtra("perfil1", masc.image1)
                intento.putExtra("perfil2", masc.image2)
                intento.putExtra("perfil3", masc.image3)
                intento.putExtra("perfil4", masc.image4)
                intento.putExtra("perfil5", masc.image5)
                intento.putExtra("perfil6", masc.image6)
                contexto!!.startActivity(intento)
            }

            return vista
        }
    }
}