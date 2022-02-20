package com.rahul.viewpagernetworkcall.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahul.viewpagernetworkcall.R
import com.rahul.viewpagernetworkcall.model.Article
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class NewsAdapter(private val context: Context, private val articles:List<Article>)
    : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val article = articles[position]

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        try {
            val time = sdf.parse(article.publishedAt).time
            val now = System.currentTimeMillis()
            val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
            holder.itemView.tvDate.text = ago
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        holder.itemView.tvHeading.text =article.title
        holder.itemView.tvDescription.text = article.description
        holder.itemView.tvSource.text = article.source.name
        Glide.with(context)
            .load(article.urlToImage)
            .into(holder.itemView.ivNewsImage)

        holder.itemView.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return articles.size
    }

}