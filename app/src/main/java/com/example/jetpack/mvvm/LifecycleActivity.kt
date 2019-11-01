package com.example.jetpack.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.jetpack.R
import kotlinx.android.synthetic.main.activity_mvvm.*

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)

        val mvCuenta = ViewModelProviders.of(this)[LifecycleViewModel::class.java]
        val ldCuenta = mvCuenta.cuentaInicial()

        ldCuenta.observe(this, Observer { cuenta ->
            tvCuenta.text = "Clicks: $cuenta"
        })

        btnPresiona.setOnClickListener {
            mvCuenta.cuentaActual()
        }
    }
}
