package com.example.jetpack.databinding.basic

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityBasicBindingBinding

class BasicBindingActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityBasicBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_binding)

        val handlers = ActivityClickHandlers(this)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_basic_binding)
        dataBinding.estudiante = obtenerEstudiante()
        dataBinding.clickHandler = handlers
    }

    private fun obtenerEstudiante(): Estudiante =
        Estudiante("Miguel", "miguel@gmail.com")

    inner class ActivityClickHandlers(val context: Context) {
        fun onInscribirButtonClick(view: View) {
            Toast.makeText(this.context, "Click Inscribir", Toast.LENGTH_SHORT).show()
        }

        fun onCancelButtonClick(view: View) {
            Toast.makeText(this.context, "Click Cancelar", Toast.LENGTH_SHORT).show()
        }
    }
}