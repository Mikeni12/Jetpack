package com.example.jetpack.databinding.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.databinding.ItemEstudianteBinding
import com.example.jetpack.databinding.Student

class StudentsAdapter(private var items: ArrayList<Student>) :
    RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEstudianteBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    fun setStudents(students: ArrayList<Student>) {
        this.items = students
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemEstudianteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var itemEstudianteBinding: ItemEstudianteBinding

        fun bind(item: Student) {
            itemEstudianteBinding = binding
            this.itemEstudianteBinding.student = item
        }
    }
}