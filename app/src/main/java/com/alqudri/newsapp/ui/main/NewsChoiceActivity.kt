package com.alqudri.newsapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alqudri.newsapp.R
import com.alqudri.newsapp.adapter.CategoryAdapter
import com.alqudri.newsapp.adapter.NewsSourceAdapter
import com.alqudri.newsapp.helper.OnItem
import com.alqudri.newsapp.helper.Static
import com.alqudri.newsapp.model.newssource.SourcesItem
import com.alqudri.newsapp.ui.news.MainActivity
import com.alqudri.newsapp.utill.hideView
import kotlinx.android.synthetic.main.activity_news_choice.*

class NewsChoiceActivity : AppCompatActivity() {
    lateinit var newsChoiceViewModel: NewsChoiceViewModel
    lateinit var onItem: OnItem
    val category = arrayListOf<String>(
        "All Categories",
        "Business",
        "Entertainment",
        "General",
        "Health",
        "Science",
        "Sports",
        "Technology"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_choice)
        newsChoiceViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(NewsChoiceViewModel::class.java)

        initInterface()
        showCategory()
        initNewsChoice()
    }

    fun initInterface() {
        onItem = object : OnItem {
            override fun onClick(position: Int) {
                rvTrigger(rv_news_category, position)
                pg_news_choice.hideView(false)
                if (position != 0){
                    newsChoiceViewModel.getNewsSource(category.get(position))
                }else{
                    newsChoiceViewModel.getNewsSource()
                }
            }

            override fun onClick(id: String) {
                val intent = Intent(this@NewsChoiceActivity, MainActivity::class.java)
                intent.putExtra("source", id)
                startActivity(intent)
            }

        }
    }

    fun initNewsChoice() {
        with(newsChoiceViewModel) {
            getNewsSource()
            listNewsSource.observe(this@NewsChoiceActivity, Observer { news ->
                if (news.status!! == Static.OK) {
                    rv_news_source.layoutManager = LinearLayoutManager(this@NewsChoiceActivity)
                    rv_news_source.adapter = NewsSourceAdapter(news.sources as List<SourcesItem>, onItem)
                    pg_news_choice.hideView(true)
                }
            })

            errorListener.observe(this@NewsChoiceActivity, Observer { error ->
                pg_news_choice.hideView(true)
                Toast.makeText(this@NewsChoiceActivity, error, Toast.LENGTH_SHORT).show()
            })
        }
    }

    fun showCategory() {
        rv_news_category.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_news_category.adapter = CategoryAdapter(category, onItem, -1)
    }

    fun rvTrigger(rv: RecyclerView, position: Int) {
        rv.adapter = CategoryAdapter(category, onItem, position)
        rv.scrollToPosition(position)
    }
}