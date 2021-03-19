package com.alqudri.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqudri.newsapp.R
import com.alqudri.newsapp.helper.OnItem
import com.alqudri.newsapp.model.newssource.SourcesItem
import kotlinx.android.synthetic.main.content_source_news.view.*

class NewsSourceAdapter(var data: List<SourcesItem>, var onItem: OnItem) : RecyclerView.Adapter<NewsSourceAdapter.ViewHolder>(){
    inner class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        fun bind(sourcesItem: SourcesItem){
            with(view){
                news_source_name.text = sourcesItem.name
                news_source_desc.text = sourcesItem.description
                bt_news_source_read.setOnClickListener {
                    sourcesItem.id?.let { it1 -> onItem.onClick(it1) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_source_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(data.get(position))
    }

}