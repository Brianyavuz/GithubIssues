package com.bysagir.githubissues

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bysagir.githubissues.model.Comment
import com.bysagir.githubissues.model.Issue


class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title: TextView = findViewById(R.id.detail_title)
        val state: TextView = findViewById(R.id.detail_state)
        val username: TextView = findViewById(R.id.detail_username)
        val description: TextView = findViewById(R.id.detail_description)
        val allcomments: TextView = findViewById(R.id.detail_comments)
        val issue = intent.getParcelableExtra<Issue>(intentIssue)
        val comments = issue?.relatedComments

        title.text = issue?.title
        state.text = issue?.state
        state.setBackgroundResource(getBackGroundColor(issue?.state))
        username.text = issue?.user?.login + " opened this issue on " + issue?.created_at
        description.text = "Description : " + issue?.body
        allcomments.text = showListOfComments(comments)
    }


    //TODO This can be recyclerview instead of one giant textview consisting comments
    //Added a scrollview and textview to make it simple
    private fun showListOfComments(comments: List<Comment>?): CharSequence {
        var formattedComments = ""
        for (comment in comments!!) {
            formattedComments = formattedComments + "Comment : " + comment.body + "\n"
        }
        return formattedComments
    }


    //TODO The business logic should not be here
    private fun getBackGroundColor(state: String?): Int {
        return if (state?.equals("open") == true) {
            R.color.colorGreen
        } else {
            R.color.colorWhite
        }
    }
}