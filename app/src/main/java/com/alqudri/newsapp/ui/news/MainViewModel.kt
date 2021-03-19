package com.alqudri.newsapp.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alqudri.newsapp.helper.Static
import com.alqudri.newsapp.model.headlines.Headlines
import com.alqudri.newsapp.network.ApiBuilder
import com.alqudri.newsapp.network.Services
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel(){
    val apiKey = Static.API_KEY
    private val request = ApiBuilder.buildService(Services::class.java)
    private val _listHeadline = MutableLiveData<Headlines>()
    val listHeadlines: LiveData<Headlines> get() = _listHeadline

    private val _listCovid = MutableLiveData<Headlines>()
    val listCovid: LiveData<Headlines> get() = _listCovid

    private val _listBusiness = MutableLiveData<Headlines>()
    val listBusiness: LiveData<Headlines> get() = _listBusiness

    private val _errorListener = MutableLiveData<String>()
    val errorListener: LiveData<String> get() = _errorListener

    private val _listSearch = MutableLiveData<Headlines>()
    val listSearch: LiveData<Headlines> get() = _listSearch

    fun getHeadlines(sources: String){
        val requestData = request.getHeadlines(apiKey, sources)

        requestData.enqueue(object : Callback<Headlines>{
            override fun onFailure(call: Call<Headlines>, t: Throwable) {
                _errorListener.postValue(t.message)
            }

            override fun onResponse(call: Call<Headlines>, response: Response<Headlines>) {
                if(response.isSuccessful){
                    _listHeadline.postValue(response.body())
                }
            }

        })
    }

    fun getCovid(sources: String){
        val requestData = request.getHeadlines(apiKey, sources, "covid19")

        requestData.enqueue(object : Callback<Headlines>{
            override fun onFailure(call: Call<Headlines>, t: Throwable) {
                _errorListener.postValue(t.message)
            }

            override fun onResponse(call: Call<Headlines>, response: Response<Headlines>) {
                if(response.isSuccessful){
                    _listCovid.postValue(response.body())
                }
            }

        })
    }

    fun getBusiness(sources: String){
        val requestData = request.getHeadlines(apiKey, sources, "business")

        requestData.enqueue(object : Callback<Headlines>{
            override fun onFailure(call: Call<Headlines>, t: Throwable) {
                _errorListener.postValue(t.message)
            }

            override fun onResponse(call: Call<Headlines>, response: Response<Headlines>) {
                if(response.isSuccessful){
                    _listBusiness.postValue(response.body())
                }
            }

        })
    }

    fun getSearch(sources: String, q: String, page: Int, pageSize: Int){
        val requestData = request.getHeadlines(apiKey, sources, q, page, pageSize)

        requestData.enqueue(object : Callback<Headlines>{
            override fun onFailure(call: Call<Headlines>, t: Throwable) {
                _errorListener.postValue(t.message)
            }

            override fun onResponse(call: Call<Headlines>, response: Response<Headlines>) {
                if(response.isSuccessful){
                    _listSearch.postValue(response.body())
                }
            }

        })
    }
}