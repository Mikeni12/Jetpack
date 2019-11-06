package com.example.jetpack.databinding.recycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityAddNewStudentBinding
import com.example.jetpack.databinding.Student

class AddNewStudentActivity : AppCompatActivity() {

    private val student by lazy { Student() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_student)

        val handler = AddNewStudentActivityClickHandler()

        val activityNewStudentBinding: ActivityAddNewStudentBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_new_student)
        activityNewStudentBinding.student = student
        activityNewStudentBinding.clickHandler = handler
    }

    inner class AddNewStudentActivityClickHandler {
        fun onSubmitClicked(view: View) {
            val isIncorrect = student.getName().isNullOrBlank() &&
                    student.getEmail().isNullOrBlank() &&
                    student.getCountry().isNullOrBlank()
            if (isIncorrect) {
                Toast.makeText(applicationContext, "Nombre vacío", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent().apply {
                    putExtra("NAME", student.getName())
                    putExtra("EMAIL", student.getEmail())
                    putExtra("COUNTRY", student.getCountry())
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}
