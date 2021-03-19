package com.alqudri.newsapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqudri.newsapp.R
import com.alqudri.newsapp.model.headlines.ArticlesItem
import com.alqudri.newsapp.ui.detail.DetailActivity
import com.alqudri.newsapp.utill.toJson
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.content_slider_small.view.*

class SmallSliderAdapter(var data: List<ArticlesItem>): RecyclerView.Adapter<SmallSliderAdapter.ViewHolder>(){
    inner class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        fun bind(articlesItem: ArticlesItem){
            with(view){
                tv_title_small_headlines.text = articlesItem.title
                tv_source_small_headlines.text = articlesItem.source?.name
                Glide.with(this).load(articlesItem.urlToImage).into(img_small_item_headlines)
                base_content_slider.setOnClickListener{
                    val intent = Intent(this.context, DetailActivity::class.java)
                    intent.putExtra("data", articlesItem.toJson())
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_slider_small, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(data.get(position))
    }

}