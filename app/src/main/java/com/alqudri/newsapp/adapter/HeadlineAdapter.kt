package com.alqudri.newsapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqudri.newsapp.R
import com.alqudri.newsapp.model.headlines.ArticlesItem
import com.alqudri.newsapp.model.headlines.Headlines
import com.alqudri.newsapp.ui.detail.DetailActivity
import com.alqudri.newsapp.utill.toJson

import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.content_slider_headline.view.*

class HeadlineAdapter(var data: List<ArticlesItem>) : SliderViewAdapter<HeadlineAdapter.ViewHolder>(){
    class ViewHolder(var view: View): SliderViewAdapter.ViewHolder(view) {
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

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.content_slider_headline, parent, false)
        return  ViewHolder(view)
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        return viewHolder!!.bind(data[position])
    }

}
