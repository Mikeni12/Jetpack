package com.example.jetpack.room

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
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
        Toast.makeText(this, "Entra", Toast.LENGTH_LONG).show()
        val layoutInflaterAndroid = LayoutInflater.from(applicationContext)
        val view = layoutInflaterAndroid.inflate(R.layout.layout_add_contact, null)

        val alertDialogBuilderUserInput = AlertDialog.Builder(this)
        alertDialogBuilderUserInput.setView(view)

        val contactTitle = view.findViewById<TextView>(R.id.new_contact_title)
        val newContact = view.findViewById<EditText>(R.id.edtNombre)
        val contactEmail = view.findViewById<EditText>(R.id.edtEmail)

        contactTitle.text = if (!actualizar) "Añadir Nuevo Contacto" else "Editar Contacto"

        if (actualizar && contacto != null) {
            newContact.setText(contacto.nombre)
            contactEmail.setText(contacto.email)
        }

        alertDialogBuilderUserInput
            .setCancelable(false)
            .setPositiveButton(
                if (actualizar) "Update" else "Save"
            ) { dialogBox, id -> }
            .setNegativeButton(
                "Delete"
            ) { dialogBox, id ->
                if (actualizar) {
                    borrarContacto(contacto!!, posicion)
                } else {

                    dialogBox.cancel()

                }
            }

        val alertDialog = alertDialogBuilderUserInput.create()
        alertDialog.show()

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(newContact.text.toString())) {
                Toast.makeText(this, "Enter contact name!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            } else {
                alertDialog.dismiss()
            }

            if (actualizar && contacto != null) {
                actualizarContacto(newContact.text.toString(), contactEmail.text.toString(), posicion)
            } else {
                crearContacto(newContact.text.toString(), contactEmail.text.toString())
            }
        })

    }
}