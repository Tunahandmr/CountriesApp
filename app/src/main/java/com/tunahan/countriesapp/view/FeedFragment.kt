package com.tunahan.countriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunahan.countriesapp.R
import com.tunahan.countriesapp.adapter.CountryAdapter
import com.tunahan.countriesapp.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = countryAdapter

        swipeRefreshLayout.setOnRefreshListener {
            recycler_view.visibility = View.GONE
            countryErrorTextView.visibility = View.GONE
            countryLoadingProgressBar.visibility = View.VISIBLE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }


   private fun observeLiveData(){

       viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                recycler_view.visibility = View.VISIBLE
                countryAdapter.updateCountryList(it)
            }
        })

       viewModel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    countryErrorTextView.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                }else{
                    countryErrorTextView.visibility = View.GONE
                }
            }
        })

       viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    countryLoadingProgressBar.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                    countryErrorTextView.visibility = View.GONE
                }else{
                    countryLoadingProgressBar.visibility = View.GONE
                }
            }
        })
    }


}