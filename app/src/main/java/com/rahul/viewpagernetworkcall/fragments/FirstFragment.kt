package com.rahul.viewpagernetworkcall.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.rahul.viewpagernetworkcall.R
import com.rahul.viewpagernetworkcall.adapter.NewsAdapter
import com.rahul.viewpagernetworkcall.api.RetrofitNews
import com.rahul.viewpagernetworkcall.model.Article
import com.rahul.viewpagernetworkcall.model.ArticleResponse
import kotlinx.android.synthetic.main.fragment_first.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FirstFragment() : Fragment() {

   private lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()

    var pageNum =1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsAdapter(requireContext(),articles)
        rvNews.adapter = adapter
        val layoutManager = LinearLayoutManager(activity)
        rvNews.layoutManager = layoutManager
        getNews()
    }

    private fun getNews() {
        val news = RetrofitNews.apiService.getHeadlines("in", pageNum)
        news.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                val news:ArticleResponse? = response.body()
                if (news!= null){
                    for (i in 0..6){
                        articles.add(news.articles[i])
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                Log.d("Main Activity","Something went wrong")
            }
        })
    }
}