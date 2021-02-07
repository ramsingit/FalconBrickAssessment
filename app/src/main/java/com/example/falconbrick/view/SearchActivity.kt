package com.example.falconbrick.view

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.falconbrick.R
import com.example.falconbrick.adapter.SearchRecyclerAdapter
import com.example.falconbrick.databinding.ActivitySearchBinding
import com.example.falconbrick.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var searchBinding: ActivitySearchBinding
    private lateinit var searchAdapter: SearchRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)

        searchAdapter = SearchRecyclerAdapter()

        initSearch()
        setViewModel()
    }

    private fun initSearch() {

        searchBinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun setViewModel(){
        val jsonData = resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        searchBinding.searchAdapter = searchAdapter
        val viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.getActivitiesList(jsonData).observe(this, { activityList ->
            searchAdapter.setList(activityList)
        })
    }
}