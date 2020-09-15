package com.ibtikar.apps.task.ui.main

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ibtikar.apps.task.R
import com.ibtikar.apps.task.api.ActorsClient
import com.ibtikar.apps.task.api.ActorsHelper
import com.ibtikar.apps.task.pojo.Actors
import com.ibtikar.apps.task.ui.factory.ViewModelFactory
import com.ibtikar.apps.task.ui.profile.ProfileActivity
import com.ibtikar.apps.task.utils.ConstantsUtils
import com.ibtikar.apps.task.utils.Status
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    MyAdapter.OnActorClickListener {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var viewModel: ActorsViewModel
    private lateinit var adapter: MyAdapter
    private var resultList: List<Actors.Result> = ArrayList()
    private lateinit var typeface: Typeface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setView()

        if (ConstantsUtils.isNetworkConnected(this)) {
            getActors();

        } else {
            showEmptyView();
        }
    }

    private fun setView() {

        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(ActorsHelper(ActorsClient.actorsInterface))
        ).get(ActorsViewModel::class.java)

        typeface = Typeface.createFromAsset(assets, "fonts/fontfamily.ttf")

        toolBarTv.typeface = typeface
        EmptyMsg.typeface = typeface

        swiperefresh.setOnRefreshListener(this)
        swiperefresh.setColorSchemeColors(resources.getColor(R.color.colorAccent))
    }

    private fun getActors() {

        EmptyMsg.visibility = View.GONE

        adapter = MyAdapter(this)
        actorsRecyclerId.layoutManager = LinearLayoutManager(this)
        actorsRecyclerId.adapter = adapter

        viewModel.getActors(
            ConstantsUtils.CATEGORY,
            ConstantsUtils.API_KEY,
            ConstantsUtils.LANGUAGE,
            ConstantsUtils.PAGE
        ).observe(this, Observer {
            it?.let { resource ->

                when (resource.status) {

                    Status.SUCCESS -> {
                        resourcesProgressBar.visibility = View.GONE
                        resource.data?.let { result -> retrieveList(result.results) }
                    }
                    Status.ERROR -> {
                        Log.d(TAG, "Error" + it.message)
                    }
                    Status.LOADING -> {
                        //Toast.makeText(this, "Please Wait", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun retrieveList(list: List<Actors.Result>) {
        resultList = list
        adapter.apply {
            setList(list)
            notifyDataSetChanged()
        }
    }

    private fun showEmptyView() {
        EmptyMsg.visibility = View.VISIBLE
        actorsRecyclerId.visibility = View.INVISIBLE
    }

    override fun onRefresh() {

        swiperefresh.isRefreshing = false
        if (ConstantsUtils.isNetworkConnected(this)) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            showEmptyView()
        }
    }

    override fun onActorClick(position: Int) {
        val intent = Intent(this, ProfileActivity::class.java)
        setIntentExtra(intent, position)
        startActivity(intent)
    }

    private fun setIntentExtra(intent: Intent, position: Int) {
        intent.putExtra("id", resultList[position].id)
        intent.putExtra("Name", resultList[position].name)
        intent.putExtra("Gender", resultList[position].gender)
        intent.putExtra("depart", resultList[position].knownForDepartment)
    }

}