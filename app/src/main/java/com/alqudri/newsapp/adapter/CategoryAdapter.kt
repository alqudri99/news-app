package com.alqudri.newsapp.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqudri.newsapp.R
import com.alqudri.newsapp.helper.OnItem
import kotlinx.android.synthetic.main.content_category.view.*

class CategoryAdapter(var dataCategory: List<String>, var onItem: OnItem, var position: Int) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
    inner class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        fun bind(category: String){
            with(view){
                tv_item_category.text = category
                if(position == this@CategoryAdapter.position){
                    base_content.setCardBackgroundColor(Color.parseColor("#3700B3"))
                    tv_item_category.setTextColor(Color.parseColor("#FFFFFF"))
                }
                base_content.setOnClickListener {
                    onItem.onClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataCategory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(dataCategory.get(position))
    }

}