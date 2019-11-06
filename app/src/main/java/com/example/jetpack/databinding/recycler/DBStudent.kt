package com.example.jetpack.databinding.recycler

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpack.databinding.Student

@Database(entities = [Student::class], version = 1)
abstract class DBStudent : RoomDatabase() {
    abstract fun obtenerDAOStudent(): DAOStudent
}