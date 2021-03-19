package com.alqudri.newsapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqudri.newsapp.R
import com.alqudri.newsapp.helper.OnItem
import com.alqudri.newsapp.model.headlines.ArticlesItem
import com.alqudri.newsapp.model.newssource.SourcesItem
import com.alqudri.newsapp.ui.detail.DetailActivity
import com.alqudri.newsapp.utill.toJson
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.content_slider_headline.view.*
import kotlinx.android.synthetic.main.content_source_news.view.*

class SearchAdapter(var data: List<ArticlesItem>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
    inner class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        fun bind(articlesItem: ArticlesItem){
            with(view){
                Glide.with(this).load(articlesItem.urlToImage).into(img_headline)
                tv_title_headline.text = articlesItem.title
                base_content_headline.setOnClickListener {
                    val intent = Intent(this.context, DetailActivity::class.java)
                    intent.putExtra("data", articlesItem.toJson())
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_slider_headline, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(data.get(position))
    }

}