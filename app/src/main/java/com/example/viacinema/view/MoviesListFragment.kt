package com.example.viacinema.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.viacinema.R
import com.example.viacinema.viewmodels.ListViewModel
import kotlinx.android.synthetic.main.activity_homepage.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment: Fragment(R.layout.activity_homepage) {
    private val viewModel: ListViewModel by inject()
    private val recyclerAdapter: FactsAdapter by lazy { FactsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = recyclerAdapter

        observeUi()
    }

    private fun observeUi() {
        viewModel.data.observe(this, Observer {
            Log.d("myLogs", it.toString())
            recyclerAdapter.updateData(it)
        })
    }
}