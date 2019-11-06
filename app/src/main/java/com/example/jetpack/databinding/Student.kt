package com.example.jetpack.databinding

import androidx.room.PrimaryKey
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.Entity
import androidx.room.Ignore


@Entity(tableName = "student_table")
class Student : BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private var studentId: Int = 0
    private var name: String? = null
    private var email: String? = null
    private var country: String? = null
    private var registeredTime: String? = null

    @Ignore
    constructor() {
    }

    constructor(
        studentId: Int,
        name: String,
        email: String,
        country: String,
        registeredTime: String
    ) {
        this.studentId = studentId
        this.name = name
        this.email = email
        this.country = country
        this.registeredTime = registeredTime
    }

    @Bindable
    fun getStudentId(): Int {
        return studentId
    }

    fun setStudentId(studentId: Int) {
        this.studentId = studentId
        notifyPropertyChanged(BR.studentId)
    }

    @Bindable
    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
        notifyPropertyChanged(BR.name)
    }

    @Bindable
    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
        notifyPropertyChanged(BR.email)
    }

    @Bindable
    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String) {
        this.country = country
        notifyPropertyChanged(BR.country)
    }

    @Bindable
    fun getRegisteredTime(): String? {
        return registeredTime
    }

    fun setRegisteredTime(registeredTime: String) {
        this.registeredTime = registeredTime
        notifyPropertyChanged(BR.registeredTime)
    }
}