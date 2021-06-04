package com.bysagir.githubissues.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bysagir.githubissues.model.Issue
import com.bysagir.githubissues.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

private const val TAG = "MainViewModel"

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val issueList = MutableLiveData<List<Issue>>()
    val errorMessage = MutableLiveData<String>()
    val errorDialog = MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun getAllIssues() {

        val issues: MutableList<Issue> = ArrayList()
        var issue: Issue? = null
        repository.getAllIssues()
            .flattenAsObservable { it }
            .flatMap {
                issue = it
                val commentsUrl = it.comments_url!!
                var commentId =
                    commentsUrl.removePrefix("https://api.github.com/repos/ReactiveX/RxJava/issues/")
                commentId = commentId.removeSuffix("/comments")
                repository.getAllComments(commentId)
            }
            .map { response ->
                response
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result ->
                    Log.i(TAG, "result->$result")
                    issue?.relatedComments = result
                    issue?.let { issues.add(it) }
                    issueList.value = issues
                },
                { error ->
                    Log.i(TAG, "error->$error.message)")
                    errorDialog.value = true

                }
            )
    }
}