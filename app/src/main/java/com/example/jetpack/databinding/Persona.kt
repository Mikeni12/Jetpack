package com.example.jetpack.databinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class Persona(
    private val _id: Long,
    private var _nombre: String,
    private var _email: String
) : BaseObservable() {

    val id: Long
        get() = _id

    var nombre: String
        @Bindable get() = _nombre
        set(value) {
            _nombre = value
            notifyPropertyChanged(BR.nombre)
        }

    var email: String
        @Bindable get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }
}