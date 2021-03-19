package com.alqudri.newsapp.utill

import android.view.View
import android.widget.EditText
import com.alqudri.newsapp.model.headlines.ArticlesItem
import com.google.gson.Gson

fun View.hideView(state: Boolean){
    if(state){
        this.visibility = View.GONE
    }else{
        this.visibility = View.VISIBLE
    }
}

fun EditText.toText(): String{
    return this.text.toString()
}

fun ArticlesItem.toJson(): String{
    val gson = Gson()
    return gson.toJson(this)
}


fun String.fromJson(): ArticlesItem{
    val gson = Gson()
    return gson.fromJson<ArticlesItem>(this, ArticlesItem::class.java)
}

