package com.alqudri.newsapp.ui.news

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alqudri.newsapp.R
import com.alqudri.newsapp.adapter.SearchAdapter
import com.alqudri.newsapp.helper.Static
import com.alqudri.newsapp.model.headlines.ArticlesItem
import com.alqudri.newsapp.utill.hideView
import com.alqudri.newsapp.utill.toText
import kotlinx.android.synthetic.main.activity_news_choice.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {
    lateinit var sources: String
    lateinit var mainViewModel: MainViewModel
    lateinit var layoutManager: LinearLayoutManager
    lateinit var searchAdapter: SearchAdapter
    private val dataList = arrayListOf<ArticlesItem>()
    var page = 1
    var isLoading = false
    val pageSize = 10
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)
        sources = activity?.intent?.getStringExtra("source")!!
        layoutManager = LinearLayoutManager(context)
        searchAdapter = SearchAdapter(dataList)
        initSearch()
        initEndlessScrolling()
        initSearchComponent()
    }

    fun initSearchComponent(){
        edt_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                page = 1
                mainViewModel.getSearch(sources, edt_search.toText(), page, pageSize)
            }

        })
    }

    fun initEndlessScrolling(){
        rv_search.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy > 0){
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = searchAdapter.itemCount
                    if (!isLoading) {
                        if ((visibleItemCount + pastVisibleItem) >= total) {
                            page++
                            getPage()
                        }

                    }
                }
            }
        })
    }

    fun getPage() {
        isLoading = true
        progressBar.hideView(!isLoading)
        mainViewModel.getSearch(sources, edt_search.toText(), page, pageSize)
    }

    fun initSearch(){
        with(mainViewModel){
            getSearch(sources, edt_search.toText(), page, pageSize)
            listSearch.observe(viewLifecycleOwner, Observer { search ->
                if(search.status!! == Static.OK){
                    val lastPosition = dataList.size -1
                    dataList.addAll(search.articles as List<ArticlesItem>)
                    dataList.reverse()
                    rv_search.layoutManager = layoutManager
                    rv_search.adapter = SearchAdapter(dataList)
                    if(isLoading){
                        rv_search.scrollToPosition(lastPosition)
                    }
                    isLoading = false
                    progressBar.hideView(!isLoading)
                }
            })

            errorListener.observe(viewLifecycleOwner, Observer { error ->
                pg_news_choice.hideView(true)
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            })
        }
    }

}