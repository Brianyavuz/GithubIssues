package com.bysagir.githubissues.network

import com.bysagir.githubissues.model.Comment
import com.bysagir.githubissues.model.Issue
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitService {
    @GET("issues")
    fun getIssues(): Single<List<Issue>>

    @GET("issues/{comment_id}/comments")
    fun getComments(@Path("comment_id") path: String): Observable<List<Comment>>

    companion object {

        private var retrofitService: RetrofitService? = null

        private var BASE_URL = "https://api.github.com/repos/ReactiveX/RxJava/"

        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}