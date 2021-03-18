package com.alqudri.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqudri.newsapp.R
import com.alqudri.newsapp.data.entities.Students
import kotlinx.android.synthetic.main.content_k.view.*

class TeAdapter(var data: List<Students>) : RecyclerView.Adapter<TeAdapter.ViewHolder>(){
    inner class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        fun bind(students: Students){
            view.ans.text = students.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_k, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       return holder.bind(data.get(position))
    }

}