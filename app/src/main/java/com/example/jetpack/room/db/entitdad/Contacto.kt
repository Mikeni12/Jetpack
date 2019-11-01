package com.example.jetpack.room.db.entitdad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactos")
data class Contacto(
    @ColumnInfo(name = "contacto_id") @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "contacto_nombre") val nombre: String,
    @ColumnInfo(name = "contacto_email") val email: String
) {
}