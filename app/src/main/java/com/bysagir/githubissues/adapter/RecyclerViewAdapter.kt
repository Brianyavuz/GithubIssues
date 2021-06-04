package com.bysagir.githubissues.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bysagir.githubissues.DetailActivity
import com.bysagir.githubissues.R
import com.bysagir.githubissues.model.Issue
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class RecyclerCustomViewAdapter(
    private val listener:OnItemClickListener
): RecyclerView.Adapter<RecyclerCustomViewAdapter.ViewHolder>(){

    var issueList= mutableListOf<Issue>()
        set (value){
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
    View.OnClickListener{
        val title: TextView = view.findViewById(R.id.issue_title)
        val user : TextView = view.findViewById(R.id.issue_user)
        val body : TextView = view.findViewById(R.id.issue_body)
        val createdAt : TextView = view.findViewById(R.id.issue_created_at)

        init{
            view.setOnClickListener(this)
        }


        override fun onClick(p0: View?) {
            listener.onItemClick(adapterPosition)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_view, viewGroup, false)

        return ViewHolder(view)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.text = "Title : " + issueList[position].title
        viewHolder.user.text =  issueList[position].user?.login + " opened the issue"
        //viewHolder.label.highlightColor = issueList[position].labels[0].color
        viewHolder.body.text = "Description : " + issueList[position].body
        viewHolder.createdAt.text = "Created at : "  + issueList[position].created_at
    }

    override fun getItemCount() = issueList.size
}