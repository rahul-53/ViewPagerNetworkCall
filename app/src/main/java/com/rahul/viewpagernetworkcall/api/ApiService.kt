package com.rahul.viewpagernetworkcall.api

import com.rahul.viewpagernetworkcall.model.ArticleResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "e993cfdeff914722b1538254dfb80b33"
const val BASE_URL = "https://newsapi.org/"



interface ApiService {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(
        @Query("country")
        country:String,
        @Query("page")
        page:Int
    ): Call<ArticleResponse>

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getCategories(
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("category")
        category:String
    ): Call<ArticleResponse>

}
object RetrofitNews{
    val apiService:ApiService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }
}