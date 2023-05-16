package mx.edu.potros.adopaws

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class mascotasAdapter (private val listaMascotas: ArrayList<mascotaR>): RecyclerView.Adapter<mascotasAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tarjeta_info_mascota, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaMascotas.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listaMascotas[position]
        holder.fechaExtravio.text = currentItem.fecha
        if (holder.nombreMascota!=null){
            holder.nombreMascota.text = currentItem.nombreM
        }else{
            holder.nombreMascota.text = ""
        }

        holder.descMascota.text = currentItem.descM
        holder.sexoMascota.text = currentItem.sexo
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nombreMascota : TextView = itemView.findViewById(R.id.tv_nombre)
        val descMascota : TextView = itemView.findViewById(R.id.tv_descripcionMascota)
        val fechaExtravio : TextView = itemView.findViewById(R.id.tv_fechaExtravio)
        val sexoMascota : TextView = itemView.findViewById(R.id.tv_sexoMascota)
        val telefonoDuenio : ImageButton = itemView.findViewById(R.id.btn_llamadaDuenio)
        val mensajeDuenio : TextView = itemView.findViewById(R.id.btn_mensajeDuenio)
    }
}