package com.example.jetpack.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.R
import com.example.jetpack.room.RoomActivity
import com.example.jetpack.room.db.entitdad.Contacto
import kotlinx.android.synthetic.main.item_contacto.view.*

class AdapterContactos(private var items: ArrayList<Contacto>, private val actividad: RoomActivity) :
    RecyclerView.Adapter<AdapterContactos.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contacto, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Contacto, position: Int) {
            itemView.tvNombre.text = item.nombre
            itemView.tvEmail.text = item.email
            itemView.setOnClickListener {
                actividad.crearYActualizarContacto(true, item, position)
            }
        }
    }
}