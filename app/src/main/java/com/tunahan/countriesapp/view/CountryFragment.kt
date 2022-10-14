package com.tunahan.countriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.tunahan.countriesapp.R
import com.tunahan.countriesapp.databinding.FragmentCountryBinding
import com.tunahan.countriesapp.util.downloadFromUrl
import com.tunahan.countriesapp.util.placeHolderProgressBar
import com.tunahan.countriesapp.view.CountryFragmentArgs
import com.tunahan.countriesapp.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {

    private lateinit var viewModel: CountryViewModel
    private var myUuid = 0
    private lateinit var dataBinding : FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            myUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(myUuid)

        observeLiveData()
    }

    private fun observeLiveData(){

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {country->
            country?.let {

                dataBinding.selectedCountry = it

                /*
                countryName.text = country.countryName
                countryCapital.text = country.countryCapital
                countryCurrency.text = country.countryCurrency
                countryRegion.text = country.countryRegion
                countryLanguage.text = country.countryLanguage
                context?.let {
                    countryFlag.downloadFromUrl(country.imageUrl, placeHolderProgressBar(it))
                }

                 */
            }
        })

    }



}