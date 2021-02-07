package com.falconbrick.view

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.falconbrick.R
import com.falconbrick.adapter.SearchRecyclerAdapter
import com.falconbrick.databinding.ActivitySearchBinding
import com.falconbrick.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var searchBinding: ActivitySearchBinding
    private lateinit var searchAdapter: SearchRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Inflates the layout using databinding **/
        searchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)
        searchAdapter = SearchRecyclerAdapter()

        initSearch()
        setViewModel()
    }

    /**
     * Initaiates search functionality for the requirement
     */
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

    /**
     * Setup ViewModel for the Search Activity(View)
     */
    private fun setViewModel() {
        val jsonData = resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        searchBinding.searchAdapter = searchAdapter
        val viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.getActivitiesList(jsonData).observe(this, { activityList ->
            searchAdapter.setList(activityList)
        })
    }
}