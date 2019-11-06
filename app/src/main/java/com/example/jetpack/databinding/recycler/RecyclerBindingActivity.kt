package com.example.jetpack.databinding.recycler

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityRecyclerBindingBinding
import com.example.jetpack.databinding.Student
import kotlinx.android.synthetic.main.activity_recycler_binding.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RecyclerBindingActivity : AppCompatActivity() {

    val NEW_STUDENT_ACTIVITY_REQUEST_CODE = 1
    val adapter = StudentsAdapter(arrayListOf())
    var students = arrayListOf<Student>()
    lateinit var studentDB: DBStudent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_binding)
        setSupportActionBar(toolbar)

        val activityRecyclerBinding: ActivityRecyclerBindingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_recycler_binding)

        val handlers = ActivityRecyclerClickHandlers()
        activityRecyclerBinding.clickHandler = handlers

        val recycler = activityRecyclerBinding.layoutContentRecycler.recyclerEstudiantes
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        studentDB =
            Room.databaseBuilder(applicationContext, DBStudent::class.java, "DBStudent").build()

        loadData()

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                rv: RecyclerView,
                vh: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val studentToDelete = students.get(viewHolder.adapterPosition)
                deleteStudent(studentToDelete)
            }
        }).attachToRecyclerView(recycler)
    }

    inner class ActivityRecyclerClickHandlers() {
        fun onFABClicked(view: View) {
            val intent = Intent(this@RecyclerBindingActivity, AddNewStudentActivity::class.java)
            startActivityForResult(intent, NEW_STUDENT_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_STUDENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.let {
                val name = data.getStringExtra("NAME")
                val email = data.getStringExtra("EMAIL")
                val country = data.getStringExtra("COUNTRY")

                val simpleDateFormat = SimpleDateFormat("EEE, d MMM yyyy")
                val currentDate = simpleDateFormat.format(Date())

                val student = Student()
                student.setName(name)
                student.setEmail(email)
                student.setCountry(country)
                student.setRegisteredTime(currentDate)

                addNewStudent(student)
            }
        }
    }

    private fun loadData() {
        GetAllStudentsAsyncTask().execute()
    }

    private inner class GetAllStudentsAsyncTask : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg params: Unit?) {
            students = ArrayList(studentDB.obtenerDAOStudent().getAllStudents())
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            adapter.setStudents(students)
        }
    }

    private fun deleteStudent(student: Student) {
        DeleteStudentAsyncTask().execute(student)
    }

    private inner class DeleteStudentAsyncTask : AsyncTask<Student, Unit, Unit>() {
        override fun doInBackground(vararg students: Student) {
            studentDB.obtenerDAOStudent().delete(students[0])
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            loadData()
        }
    }


    private fun addNewStudent(student: Student) {
        AddNewStudentAsyncTask().execute(student)
    }

    private inner class AddNewStudentAsyncTask : AsyncTask<Student, Unit, Unit>() {
        override fun doInBackground(vararg students: Student) {
            studentDB.obtenerDAOStudent().insert(students[0])
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            loadData()
        }
    }
}
