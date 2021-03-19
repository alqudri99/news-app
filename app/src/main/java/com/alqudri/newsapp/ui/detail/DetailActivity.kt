package com.alqudri.newsapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alqudri.newsapp.R
import com.alqudri.newsapp.model.headlines.ArticlesItem
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    lateinit var data: ArticlesItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent.getStringExtra("data")
        val gson = Gson()
        data = gson.fromJson<ArticlesItem>(intent, ArticlesItem::class.java)

        Glide.with(this).load(data.urlToImage).into(img_detail_content)
        tv_detail_title.text = data.title
        tv_detail_content.text = data.description
        btn_full.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", data.url)
            startActivity(intent)
        }
        btn_share.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                """
                    Check out this news! Send from MyTimes App
                    ${Uri.parse(data.url)}
                    """.trimIndent()
            )
            startActivity(Intent.createChooser(shareIntent, "Share with"))
        }
    }
}