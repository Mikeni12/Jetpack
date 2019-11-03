package com.example.jetpack.databinding.advanced

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack.R
import com.example.jetpack.databinding.Estudiante
import kotlinx.android.synthetic.main.activity_advanced_binding.*

class AdvancedBinding : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advanced_binding)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun obtenerEstudiante(): Estudiante = Estudiante("Miguel", "miguel@gmail.com")


}