package com.example.jetpack.room.db

import androidx.room.*
import com.example.jetpack.room.db.entitdad.Contacto

@Dao
interface DAOContacto {

    @Insert
    fun añadirContacto(contacto: Contacto): Long

    @Update
    fun actualizarContacto(contacto: Contacto)

    @Delete
    fun borrarContacto(contacto: Contacto)

    @Query("select * from contactos")
    fun obtenerContactos(): List<Contacto>

    @Query("select * from contactos where contacto_id ==:contactoId")
    fun obtenerContacto(contactoId: Long): Contacto

}