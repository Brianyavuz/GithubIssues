package com.bysagir.githubissues

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bysagir.githubissues.adapter.RecyclerCustomViewAdapter
import com.bysagir.githubissues.model.Comment
import com.bysagir.githubissues.network.RetrofitService
import com.bysagir.githubissues.repository.MainRepository
import com.bysagir.githubissues.viewmodel.MainViewModel
import com.bysagir.githubissues.viewmodel.MyViewModelFactory
import com.google.gson.Gson
import kotlinx.coroutines.*

const val intentIssue = "ISSUE"
class MainActivity : AppCompatActivity(), RecyclerCustomViewAdapter.OnItemClickListener {
    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java
        )
        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val adapter = RecyclerCustomViewAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)


        viewModel.issueList.observe(this, Observer {
            adapter.issueList = it.toMutableList()
        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllIssues()

        viewModel.errorDialog.observe(this, Observer {
            if(it) {
                Toast.makeText(this, getString(R.string.API_error_dialog), Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun onItemClick(position: Int) {

        intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(intentIssue, viewModel.issueList.value!![position])
        startActivity(intent)
    }
}