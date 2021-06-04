package com.bysagir.githubissues.repository

import com.bysagir.githubissues.model.Comment
import com.bysagir.githubissues.model.Issue
import com.bysagir.githubissues.network.RetrofitService
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call


class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllIssues(): Single<List<Issue>> = retrofitService.getIssues()
    fun getAllComments(path:String): Observable<List<Comment>> = retrofitService.getComments(path)
}