package com.alqudri.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alqudri.newsapp.R
import com.alqudri.newsapp.adapter.HeadlineAdapter
import com.alqudri.newsapp.adapter.SmallSliderAdapter
import com.alqudri.newsapp.helper.Static
import com.alqudri.newsapp.model.headlines.ArticlesItem
import com.alqudri.newsapp.utill.hideView
import kotlinx.android.synthetic.main.activity_news_choice.*
import kotlinx.android.synthetic.main.fragment_headline.*

class HeadlineFragment : Fragment() {
    lateinit var sources: String
    lateinit var mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        rv_small_business.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_small_covid.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        sources = activity?.intent?.getStringExtra("source")!!
        initNews()

    }

    fun initNews() {
        with(mainViewModel) {
            getHeadlines(sources)
            getCovid(sources)
            getBusiness(sources)
            listHeadlines.observe(viewLifecycleOwner, Observer { sources ->
                if (sources.status!! == Static.OK){
                    rv_headline.setSliderAdapter(HeadlineAdapter(sources.articles as List<ArticlesItem>))
                }
            })

            listBusiness.observe(viewLifecycleOwner, Observer { business ->
                if (business.status!! == Static.OK){
                    rv_small_business.adapter = SmallSliderAdapter(business.articles as List<ArticlesItem>)
                }
            })

            listCovid.observe(viewLifecycleOwner, Observer { covid ->
                if (covid.status!! == Static.OK){
                    rv_small_covid.adapter = SmallSliderAdapter(covid.articles as List<ArticlesItem>)
                }
            })

            errorListener.observe(viewLifecycleOwner, Observer { error ->
                pg_news_choice.hideView(true)
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            })

        }
    }

}