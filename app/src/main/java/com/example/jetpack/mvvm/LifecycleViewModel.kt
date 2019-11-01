package com.example.jetpack.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LifecycleViewModel : ViewModel() {

    private var cuenta = 0
    private val liveDataCuenta = MutableLiveData<Int>()

    fun cuentaInicial(): MutableLiveData<Int> = liveDataCuenta.apply { value = cuenta }

    fun cuentaActual() {
        liveDataCuenta.value = ++cuenta
    }
}