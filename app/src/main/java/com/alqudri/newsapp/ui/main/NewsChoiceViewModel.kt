package com.alqudri.newsapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alqudri.newsapp.helper.Static
import com.alqudri.newsapp.model.Error
import com.alqudri.newsapp.model.newssource.NewsSource
import com.alqudri.newsapp.network.ApiBuilder
import com.alqudri.newsapp.network.Services
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsChoiceViewModel: ViewModel(){
    val request = ApiBuilder.buildService(Services::class.java)
    val apiKey = Static.API_KEY
    private val _listNewsSource = MutableLiveData<NewsSource>()
    val listNewsSource: LiveData<NewsSource> get() = _listNewsSource
    private val _errorListener = MutableLiveData<String>()
    val errorListener: LiveData<String> get() = _errorListener


    fun getNewsSource(category: String){
        val requestDAta = request.getNewsSource(category, apiKey)
        requestDAta.enqueue(object : Callback<NewsSource>{
            override fun onFailure(call: Call<NewsSource>, t: Throwable) {
                _errorListener.postValue(t.message)
            }

            override fun onResponse(call: Call<NewsSource>, response: Response<NewsSource>) {
                if (response.isSuccessful){
                    _listNewsSource.postValue(response.body())
                }
            }

        })
    }

    fun getNewsSource(){
        val requestDAta = request.getNewsSource(apiKey)
        requestDAta.enqueue(object : Callback<NewsSource>{
            override fun onFailure(call: Call<NewsSource>, t: Throwable) {
                _errorListener.postValue(t.message)
            }

            override fun onResponse(call: Call<NewsSource>, response: Response<NewsSource>) {
                if (response.isSuccessful){
                    _listNewsSource.postValue(response.body())
                }
            }

        })
    }
}