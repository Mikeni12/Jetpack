package com.example.jetpack.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpack.room.db.entitdad.Contacto

@Database(entities = [Contacto::class], version = 1)
abstract class DBContactos : RoomDatabase() {
    abstract fun obtenerDAOContacto(): DAOContacto
}