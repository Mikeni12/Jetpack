package com.example.jetpack

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack.mvvm.LifecycleActivity
import com.example.jetpack.room.RoomActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnMVVM.setOnClickListener { this.iniciarActividad<LifecycleActivity>() }
        btnROOM.setOnClickListener { this.iniciarActividad<RoomActivity>() }
    }

    inline fun <reified T : Activity> Context.iniciarActividad(intentBlock: Intent.() -> Unit = {}) {
        val intent = Intent(this, T::class.java)
        intent.intentBlock()
        startActivity(intent)
    }
}