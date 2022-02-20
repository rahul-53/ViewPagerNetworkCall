package com.rahul.viewpagernetworkcall.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.viewpagernetworkcall.R
import com.rahul.viewpagernetworkcall.adapter.NewsAdapter
import com.rahul.viewpagernetworkcall.api.RetrofitNews
import com.rahul.viewpagernetworkcall.model.Article
import com.rahul.viewpagernetworkcall.model.ArticleResponse
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_third.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecondFragment : Fragment() {

    private lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()

    var pageNum = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NewsAdapter(requireContext(), articles)
        rvNews2.adapter = adapter
        val layoutManager = LinearLayoutManager(activity)
        rvNews2.layoutManager = layoutManager
        getNews()
    }

    private fun getNews() {
        val news = RetrofitNews.apiService.getHeadlines("in", pageNum)
        news.enqueue(object : Callback<ArticleResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                val news: ArticleResponse? = response.body()
                if (news != null) {
                    for (i in 7..12){
                        articles.add(news.articles[i])
                    }
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                Log.d("Main Activity", "Something went wrong")
            }
        })
    }

}


