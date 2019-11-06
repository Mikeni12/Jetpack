package com.example.jetpack.databinding.recycler

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.jetpack.databinding.Student

@Dao
interface DAOStudent {
    @Insert
    fun insert(student: Student)

    @Query("SELECT * FROM student_table")
    fun getAllStudents(): List<Student>

    @Delete
    fun delete(student: Student)
}