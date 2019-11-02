package com.example.jetpack.room

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.jetpack.R
import com.example.jetpack.room.adapter.AdapterContactos
import com.example.jetpack.room.db.DAOContacto
import com.example.jetpack.room.db.DBContactos
import com.example.jetpack.room.db.entitdad.Contacto
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.dialog_contacto.view.*

class RoomActivity : AppCompatActivity() {

    private lateinit var adapterContactos: AdapterContactos
    private lateinit var dbContactos: DBContactos
    private lateinit var daoContactos: DAOContacto
    private lateinit var contactos: ArrayList<Contacto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        dbContactos = Room.databaseBuilder(
            applicationContext,
            DBContactos::class.java,
            "DBContactos"
        ).allowMainThreadQueries().build()

        daoContactos = dbContactos.obtenerDAOContacto()
        contactos = ArrayList(daoContactos.obtenerContactos())

        adapterContactos = AdapterContactos(contactos, this)
        val recyclerContacts = findViewById<RecyclerView>(R.id.recyclerContacts)
        recyclerContacts.layoutManager = LinearLayoutManager(this)
        recyclerContacts.itemAnimator = DefaultItemAnimator()
        recyclerContacts.adapter = adapterContactos

        fab.setOnClickListener { view ->
            crearYActualizarContacto(false, null, -1)
        }
    }

    private fun actualizarContacto(nombre: String, email: String, posicion: Int) {
        val contacto = contactos[posicion]
        contacto.nombre = nombre
        contacto.email = email

        daoContactos.actualizarContacto(contacto)
        contactos[posicion] = contacto
        adapterContactos.notifyDataSetChanged()
    }

    private fun borrarContacto(contacto: Contacto, posicion: Int) {
        contactos.removeAt(posicion)
        daoContactos.borrarContacto(contacto)
        adapterContactos.notifyDataSetChanged()
    }

    private fun crearContacto(nombre: String, email: String) {
        val id = daoContactos.crearContacto(Contacto(0, nombre, email))
        val contacto = daoContactos.obtenerContacto(id)
        contacto?.let {
            contactos.add(0, it)
            adapterContactos.notifyDataSetChanged()
        }
    }

    fun crearYActualizarContacto(actualizar: Boolean, contacto: Contacto?, posicion: Int) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_contacto, null)
        val toast = Toast.makeText(this, "Ingresa el nombre del contacto", Toast.LENGTH_SHORT)

        mDialogView.tvTitulo.text = if (!actualizar) "Agregar Contacto" else "Editar Contacto"
        if (actualizar && contacto != null) {
            mDialogView.edtNombre.setText(contacto.nombre)
            mDialogView.edtEmail.setText(contacto.email)
        }

        AlertDialog.Builder(this).apply {
            setView(mDialogView)
            setCancelable(false)
            setPositiveButton(if (actualizar) "Actualizar" else "Guardar") { _, _ -> }
            setNegativeButton("Borrar") { dialogBox, _ ->
                if (actualizar) borrarContacto(contacto!!, posicion) else dialogBox.cancel()
            }
        }.create().also { dialog ->
            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val nombre = mDialogView.edtNombre.text.toString()
                val email = mDialogView.edtEmail.text.toString()
                if (TextUtils.isEmpty(nombre)) toast.show() else dialog.dismiss()

                if (actualizar && contacto != null) {
                    actualizarContacto(nombre, email, posicion)
                } else {
                    crearContacto(nombre, email)
                }

            }
        }
    }
}