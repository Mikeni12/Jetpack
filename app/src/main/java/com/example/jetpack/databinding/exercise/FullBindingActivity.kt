package com.example.jetpack.databinding.exercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityFullBindingBinding
import com.example.jetpack.databinding.Persona

class FullBindingActivity : AppCompatActivity() {

    private lateinit var fullBindingActivity: ActivityFullBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_binding)

        val persona = Persona(1, "Miguel", "miguel@gmail.com")

        fullBindingActivity = DataBindingUtil.setContentView(this, R.layout.activity_full_binding)
        fullBindingActivity.persona = persona
    }
}
