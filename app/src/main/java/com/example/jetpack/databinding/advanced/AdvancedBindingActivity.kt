package com.example.jetpack.databinding.advanced

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityAdvancedBindingBinding
import com.example.jetpack.databinding.Estudiante
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_advanced_binding.*

class AdvancedBindingActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityAdvancedBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advanced_binding)
        setSupportActionBar(toolbar)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_advanced_binding)
        dataBinding.estudiante = obtenerEstudiante()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun obtenerEstudiante(): Estudiante = Estudiante("Miguel", "miguel@gmail.com")

}
